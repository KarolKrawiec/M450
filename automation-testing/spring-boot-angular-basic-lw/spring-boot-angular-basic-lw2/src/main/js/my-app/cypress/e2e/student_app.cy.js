describe('Student App', () => {
    it('should load the home page and navigate to student list', () => {
        cy.visit('/');
        cy.contains('List Students').should('be.visible');
        cy.contains('Add Students').should('be.visible');

        cy.contains('List Students').click();
        cy.url().should('include', '/students');
        cy.contains('Name'); // Assuming table header
        cy.contains('Email'); // Assuming table header
        cy.contains('Phone');
    });
});
