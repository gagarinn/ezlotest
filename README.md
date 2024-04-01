eZLO Android Case Study

Build an app that displays a list as on the attached mockup. To get the list of items you need to make a call to https://veramobile.mios.com/test_android/items.test

The list must be sorted by PK_Device(this is the same with SN from the mocks) and must have a header with a picture and your name.
The item icon is displayed depending on "Platform" key:
"Sercomm G450" -> vera_plus_big
"Sercomm G550" -> vera_secure_big
"MiCasaVerde VeraLite" -> vera_edge_big
"Sercomm NA900" -> vera_edge_big
"Sercomm NA301" -> vera_edge_big
"Sercomm NA930" -> vera_edge_big
no platform key -> vera_edge_big
List Item titles should be generated
On the item click open a new page that has the same header and the item details.
On item Long click a dialog must be displayed with the delete option and two buttons ok and cancel. On delete selected the item must be removed from the list, on cancel the user must see the same list.
The fetched list should be stored by using a database. A “Reset”  button should be located on the top of the screen. And the button should provide a way to fetch the data from the API and reset the items on the db and on the list.
Put an edit Icon somewhere on the list items and when click on it, open the same detail page with Edit Mode. (In edit mode, Only the Title field -Home Number 1–  should be editable. The user expects to see the title in an EditText and the keyboard is visible). There should be a way to save changes. When the user back to the list page, the related item should be refreshed with new values.
MVVM Architecture Component Usage is a must
Using a git repository is a must.
