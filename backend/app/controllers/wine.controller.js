const db = require("../models");
const Wine = db.wines;
const Op = db.Sequelize.Op;

// Create and Save a new Wine
// req --> request (contains the body)
exports.create = (req, res) => {
  // Validate request
  if (!req.body.brand || !req.body.model) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a Wine
  const wine = {
    name: req.body.name,
    type: req.body.type,
    description: req.body.description,
    price: req.body.price,
    quantity: req.body.quantity
  };

  // Save Wine in the database
  Wine.create(wine)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Wine."
      });
    });
};

// Retrieve all wine from the database.
exports.findAll = (req, res) => {
  
    Wine.findAll()
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving wines."
        });
      });
};

// Find a single Wine with an id
exports.findOne = (req, res) => {
  let id = req.params.id;
  Wine.findByPk(id)
    .then(data => {
      console.log("estos son los datos")
      console.log(data);
      if(!data){
        res.status(400).send({
          message:
            "No Wine found with that id"
        })
      }
      res.send(data);
      return;
    })
    .catch(err => {
      console.log(err.message);
      console.log("hola");
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving wine with id"
      });
      return;
    });
};

// Update a Tutorial by the id in the request
exports.update = (req, res) => {
  
};

// Delete a Tutorial with the specified id in the request
exports.delete = (req, res) => {
  
};

// Delete all Tutorials from the database.
exports.deleteAll = (req, res) => {
  
};