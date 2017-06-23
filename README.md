# Community College Coding Challenge
@authors Vergara, Alfred | Agonoy, Benie | Barcelo, Glen (ABV Coding)
## Watchman App

This application utilizes a button. Upon release of the button, a SMS message is sent to a user defined emergency contact (telephone number) which includes a user defined message and a link to the user's most recent GPS coordinates. This app is intended for use in case of emergency, when the user is feeling unsafe. The user will be able to utilize the simple button press of the application in order to send an SOS to an emergency contact.

### Basis on Security

This application was inspired by the instant response that Batman is able to provide to Gotham by the simple flip of a switch, and the bat signal in the sky. We want to empower our users with a single press of a button, by providing them the ability to quickly contact an emergency contact and let them know where they are experiencing distress. This can also be used with emergency services within certain areas.

### Technical Implementations

The team utilized Google Maps API, SMS and GPS services, simple storage of contacts, interaction of multiple fragments, and a clean GUI utilizing XML. Although the logic and ideas to implement these solutions were simple, the learning curve of both Android Studio and its unique classes felt frustrating and challenging. 

## Menu Navigation
(Buttons)

Tracking - primary functionality of the application, sends SMS and GPS coordinates

Last Route - Google maps screen, displays your last known location based on last button press in Tracking.

Settings - a user can store emergency contact info

## Prerequisites
The files within this repository were created using Android Studio, and must be imported as a project within Android Studio. From there, you can open the project by building and running the project within an emulator of your choice.

## Known Bugs
The application crashes upon initial installation before the user grants location and SMS service use permissions. 

## Future Plans
Implement ability to have multiple users interact with each other on application
Allow for ability to plot or record gps locations over periods of time, in order to give emergency contacts an idea of a vague route that the damsal's device traveled. This is the intention of the Last Route menu; it is intended to display map pinpoints of the many polled places over time in order to sketch a vague idea of the user's device route.
