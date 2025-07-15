fetch("http://localhost:8080/PokemonTCG/cards")
  .then(response => response.json())
  .then(data => {
    const tbody = document.querySelector("#card-table tbody");
    data.forEach(card => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${card.name}</td>
        <td>${card.setName}</td>
        <td>${card.rarity}</td>
        <td>${card.quantity}</td>
        <td>${card.marketPrice.toFixed(2)}</td>
      `;
      tbody.appendChild(row);
    });
  })
  .catch(error => console.error("Error loading cards:", error));

  