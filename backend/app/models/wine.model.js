module.exports = (sequelize, Sequelize) => {
    const Wine = sequelize.define("wines", {
      name: {
        type: Sequelize.STRING
      },
      type: {
        type: Sequelize.STRING
      },
      description: {
          type: Sequelize.STRING
      },
      price: {
        type: Sequelize.INTEGER
    },
      quantity: {
        type: Sequelize.INTEGER
    },
    }, { timestamps: false});
  
    return Wine;
  };