# Frontend

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 17.0.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.


app.component.html:
This is the root component template file, serving as the main view of your Angular application. It includes UI elements such as buttons, input boxes, response area, and history section. Utilize Angular's data and event binding syntax to connect the template with the component class.

app.component.ts:
This is the TypeScript class file for the root component, handling data and logic within the template. In this file, create properties and methods for models (for example, store name and initial value), such as methods for creating and displaying stores.

app.component.css:
This file contains the styles for the root component. Add CSS rules to beautify your template in line with the UI design. Write CSS to match the layout and style of your UI screenshot.

app.routes.ts:
For multiple views or pages, use this file to define routes. Set up routes to navigate among different components and views.

app.config.ts and app.config.server.ts:
Configure any API endpoints or global settings here. Define any global or server-side configurations.

app.component.spec.ts:
This file is for the unit tests of the root component. Remember to update the tests when adding new features to ensure they work as expected.