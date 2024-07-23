**App Screenshots:**

<img width="374" alt="Screenshot 2024-07-23 at 14 21 24" src="https://github.com/user-attachments/assets/aa3111fd-8840-40fb-9b4a-e0fca41e4000">
<img width="363" alt="Screenshot 2024-07-23 at 14 21 55" src="https://github.com/user-attachments/assets/140f6d86-2290-4a88-950b-67aef4b1afeb">

**Decisions:**

- Persistence of favorite cat id to **Room** database on clicking Heart Icon.
- Each item in **LazyColumn** is independently observing the favorites database through LiveData in **FavoritesViewModel**
  
**Architecture:**

<u>Dependency Inversion:</u>
  - The Repository class has three dependencies injected.
  - Each dependency can be mocked separately and the repository can be tested for appropriate behaviour.
  - This helps us follow **Dependency Inversion** part of **SOLID** principle.

<u>Open Closed Principle:</u>
  - ApiMapper is an interface that can implemented to customize for each feature API call.
  - This was done to follow **Open Closed Principle**.
  - The ApiMapper interface will have reserved implementation of converting Input datatype to Output datatype.
  - At the same time, the extended class will have the open flexibility of providing how the Input and Output datatype will be converted.

