module.exports = {
    HOST: "localhost",
    USER: "root",
    PASSWORD: "alumno2021",
    DB: "Cellar_DB",
    dialect: "mysql",
    pool: {
      max: 5,
      min: 0,
      acquire: 30000,
      idle: 10000
    }
  };