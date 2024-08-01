# Intro
Simple Java Chess Application

# Helpful Design Patterns
## 1. Model-View-Controller (MVC)
The MVC pattern helps separate the game's logic, user interface, and control flow. This makes the application easier to manage and scale.

### Model: 
Represents the game's data and business logic (e.g., the chessboard, pieces, and rules).
### View: 
Represents the UI components that display the game state to the player (e.g., the chessboard GUI).
### Controller:
Handles user input and updates the model and view accordingly.
## 2. Observer
The Observer pattern is useful for handling the updates between the model and the view. When the game state changes (e.g., a piece moves), observers (views) are notified to update their display.

## 3. Command
The Command pattern can be used to encapsulate the actions of the player (e.g., moving a piece). This is useful for implementing features like undo/redo functionality.

## 4. Singleton
The Singleton pattern ensures that certain components, such as the game manager or logger, have only one instance throughout the application.

## 5. Factory
The Factory pattern can be used to create different types of chess pieces (e.g., pawn, rook, knight). This helps in managing the creation logic in one place.

## 6. State
The State pattern can be useful for managing the different states of the game (e.g., selecting a piece, moving a piece, game over). This can help simplify the control flow of the game.

## 7. Strategy
The Strategy pattern can be used for implementing different algorithms for computer opponents or move validation strategies.
