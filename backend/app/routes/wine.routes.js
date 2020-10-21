module.exports = app => {
    const wines = require("../controllers/wine.controller.js");
  
    var router = require("express").Router();
  
    // Create a new wine
    router.post("/", wines.create);
  
    // Retrieve all wines
    router.get("/", wines.findAll);
  
    // Retrieve a single wine with id
    router.get("/:id", wines.findOne);
  
    // Update a wine with id
    router.put("/:id", wines.update);
  
    // Delete a wine with id
    router.delete("/:id", wines.delete);
  
  
    app.use('/api/wines', router);
  };