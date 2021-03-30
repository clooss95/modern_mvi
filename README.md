# Modern MVI
##### This project contains a library that helps implement the MVI design pattern in an android application and examples of how to use the library.

### 1. What is MVI in general?

![mvi chart](https://github.com/clooss95/modern_mvi/blob/master/images/mvi_chart.png?raw=true)
##### MVI stands for Model-View-Intent. MVI is one of the newest architecture patterns for Android

MVI works in a very different way compared to its distant relatives, MVC, MVP or MVVM. The role of each MVI components is as follows:

- Model represents a state. Models in MVI should be immutable to ensure a unidirectional data flow between them and the other layers in your architecture.
- Views collect intents emitted by the user and render state on the screen.
- Intent represents an intention or a desire to perform an action, either by the user or the app itself. For every action, a View receives an Intent.

### 2. How the MVI works with this library?
This library contains code that makes it easy for us to implement the MVI pattern and reduce boiler plate.
The implementation is based on several basic components:
- ViewState - state of a given screen
- ViewEffect - effect that can occur on a given screen (f.e. showing a snackbar or navigation action)
- Intent - an intention or a desire to perform an action (f.e. clicking a button or typing text)
- Presenter - object that observes the Intents and maps thems into PartialStates
- PartialState - object that updates the ViewState using "reduce" method
- MviActivity - activity class containing boiler plate code required by MVI flow
- MviFragment - activity class containing boiler plate code required by MVI flow

##### Let's try it on an example

Suppose we are to implement a counter screen. This screen displays the current counter status starting from 0.
The user has the possibility to click the "increase" button, which increases the counter value by 1, or "decrease" button, which decreases the counter value by 1. The user can also press the navigation button which will take him to the next screen. Sounds simple, isn't it?

![sample screenshot](https://github.com/clooss95/modern_mvi/blob/master/images/screenshot1.png?raw=true)

