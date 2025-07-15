document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("card-form");
  const tableBody = document.querySelector("#collection-table tbody");

  function loadCards() {
    fetch("cards")
      .then(res => res.json())
      .then(data => {
        tableBody.innerHTML = "";
        data.forEach(card => {
          const row = document.createElement("tr");
          row.innerHTML = `
            <td>${card.setID}</td>
            <td>${card.name}</td>
            <td>${card.setName}</td>
            <td>${card.holo ? "✔️" : ""}</td>
            <td>${card.quantity}</td>
            <td>$${card.price.toFixed(2)}</td>
            <td>${card.promo}</td>
          `;
          tableBody.appendChild(row);
        });
      })
      .catch(err => console.error("Failed to load cards:", err));
  }

  form.addEventListener("submit", event => {
    event.preventDefault();
    const formData = new FormData(form);
    const card = {
      name: formData.get("name"),
      setName: formData.get("setName"),
      setID: parseInt(formData.get("setID")),
      holo: formData.get("holo") === "on",
      quantity: parseInt(formData.get("quantity")),
      price: parseFloat(formData.get("price")),
      promo: formData.get("promo") || "None"
    };

    fetch("cards", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(card)
    })
    .then(res => {
      if (res.ok) {
        loadCards();
        form.reset();
      } else {
        alert("Failed to add card.");
      }
    });
  });

  loadCards(); // initial load
});