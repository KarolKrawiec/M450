describe("Student App", () => {
  it("should load the main page", () => {
    cy.visit("http://localhost:4200/");

    cy.contains("List Students");
    cy.contains("Add Students");
  });

  it("should display validation errors when form is submitted empty", () => {
    cy.visit("http://localhost:4200/");

    cy.get('a').contains('Add Students').should('be.visible').click();

    cy.get('button[type="submit"]').click({ force: true });

    cy.contains("Name is required").should("be.visible");
    cy.contains("Email is required").should("be.visible");
  });

  it("should fill out the form and submit successfully", () => {
    cy.visit("http://localhost:4200/");

    cy.get('a').contains('Add Students').should('be.visible').click();

    cy.get('input[placeholder="Enter your name"]').type("John Doe");
    cy.get('input[placeholder="Enter your email address"]').type("john.doe@example.com");

    cy.get('button[type="submit"]').click();

    cy.wait(100);

    cy.visit("http://localhost:4200/students");
    cy.contains("John Doe").should("be.visible");
  });

  it("should display all students when clicking the List Students button", () => {
    cy.visit("http://localhost:4200/");

    cy.get('a').contains('List Students').should('be.visible').click();

    cy.wait(100);

    cy.contains("Max").should("be.visible");
    cy.contains("Patrick").should("be.visible");
    cy.contains("Yves").should("be.visible");
    cy.contains("Peter").should("be.visible");
    cy.contains("Ann").should("be.visible");

    cy.contains("John Doe").should("be.visible");
  });
});
