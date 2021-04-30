# SOS_app

## Use of the app
The app is built for the purpose of providing an SOS feature that the user can use when the user is in a dangerous area, if they get in trouble. It can be used by women, children, people from minority groups, those who face hate in the community and could be targetted

## Description of the app
- When the user first uses the app, they are required to add atleast 1 emergency contact and give permissions for SMS and location.
- When the user gets in trouble, they can either click on the button or shake the phone. An SMS will be sent to their emergency contacts with a google maps link of their current location.
- If the user is sure that they are going to a safe place and phone might shake even if there is no trouble, user can turn off the shake detector in the settings.
- The content of the SMS and background color of the app can be changed based on the user's preferences. 

## Concepts used
- Sensor Event Listener for detecting shaking of phone
- Location Listener for getting current location of the user
- SMS Manager for sending SMS when user finds trouble
- SQLite Database to store emergency contacts of the user
- Shared preferences to store user's preferred appearances of the app
- Recycler view to list contacts
