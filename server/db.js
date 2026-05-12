// db.js
const { Pool } = require('pg');

const pool = new Pool({
  user: 'postgres',
  host: 'localhost',
  database: 'medecin_db',
  password: 'rijaniaina',
  port: 5432,
});

module.exports = pool;