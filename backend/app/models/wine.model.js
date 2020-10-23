module.exports = (sequelize, Sequelize) => {
    const Wine = sequelize.define("wines", {
      name: {
        type: Sequelize.STRING,
        allowNull: false
      },
      type: {
        type: Sequelize.STRING,
        allowNull: false
      },
      description: {
          type: Sequelize.STRING,
          allowNull: true
      },
      price: {
        type: Sequelize.INTEGER,
        allowNull: true
    },
      quantity: {
        type: Sequelize.INTEGER,
        allowNull: true
    },
    }, { timestamps: false});
  
    return Wine;
  };