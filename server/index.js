// index.js
const express = require('express');
const pool = require('./db');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

// Ajouter
app.post('/medecins', async (req, res) => {
  const { nom, nombre_jour, taux_journalier } = req.body;

  const result = await pool.query(
    'INSERT INTO medecin (nom, nombre_jour, taux_journalier) VALUES ($1,$2,$3) RETURNING *',
    [nom, nombre_jour, taux_journalier]
  );

  res.json(result.rows[0]);
});

// Lire prestation
app.get('/medecins', async (req, res) => {
  const result = await pool.query(`
    SELECT *, (nombre_jour * taux_journalier) AS prestation
    FROM medecin
  `);
  res.json(result.rows);
});

// Modification 
app.put('/medecins/:id', async (req, res) => {
  const { id } = req.params;
  const { nom, nombre_jour, taux_journalier } = req.body;

  await pool.query(
    'UPDATE medecin SET nom=$1, nombre_jour=$2, taux_journalier=$3 WHERE numMed=$4',
    [nom, nombre_jour, taux_journalier, id]
  );

  res.send("Updated");
});

// Suppression
app.delete('/medecins/:id', async (req, res) => {
  const { id } = req.params;
  await pool.query('DELETE FROM medecin WHERE numMed=$1', [id]);
  res.send("Deleted");
});

// Statistique
app.get('/stats', async (req, res) => {
  const result = await pool.query(`
    SELECT 
      MIN(nombre_jour * taux_journalier) AS min,
      MAX(nombre_jour * taux_journalier) AS max,
      SUM(nombre_jour * taux_journalier) AS total
    FROM medecin
  `);

  res.json(result.rows[0]);
});

// Serveur
app.listen(3000, () => console.log("Server running on port 3000"));