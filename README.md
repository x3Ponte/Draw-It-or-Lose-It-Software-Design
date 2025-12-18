# Draw It or Lose It Software Design
Software design documentation and Java prototype for 'Draw It or Lose It,' a multi platform web game. Demonstrates Singleton/Iterator patterns, Linux server architecture analysis, and object oriented design.

## Professional Self Assessment

### 1. Client Summary and Requirements
**Who was the client? What type of software did they want you to design?**

The client, **The Gaming Room**, is a company that developed a local Android based game called "Draw It or Lose It" (similar to Pictionary). They wanted to modernize this legacy application and expand it into a fully distributed, web based system. Their primary goal was to move away from being Android only and create a platform independent game that could be played in a web browser on any device (iOS, Android, Windows, Mac, Linux).

From a technical standpoint, they required a server side Java application to manage the game state. Key functional requirements included supporting multiple teams and players, ensuring that all game and team names were unique to prevent confusion, and guaranteeing that only a single instance of the game service existed in memory to maintain a consistent state for all connected players.

### 2. Documentation Strengths
**What did you do particularly well in developing this documentation?**

I believe the **Evaluation** section of my design document was particularly strong. I was able to clearly distinguish between the "Server Side" and "Client Side" needs, rather than lumping them together. For example, I accurately identified that while Linux is the superior choice for the server due to its stability and lack of licensing fees, the client side development needed to focus on responsive web design (HTML/CSS) rather than native OS development. This distinction is critical for the client’s budget and timeline, and I think I communicated those trade offs effectively.

### 3. The Design Process
**What about the process of working through a design document did you find helpful when developing the code?**

Writing the **Design Constraints** section before writing the code was surprisingly helpful. By explicitly writing out the implication of "Platform Independence"—specifically that the backend must be completely decoupled from the UI it clarified exactly how I needed to write the Java classes. It kept me from trying to put any display logic inside the `Game` or `Team` classes. It enforced the idea that the Java code was just an API/engine, and the "Constraint" section served as a constant reminder of that architecture while I was programming.

### 4. Areas for Improvement
**If you could choose one part of your work on these documents to revise, what would you pick? How would you improve it?**

If I could revise one section, it would be the **Domain Model** description. While I correctly identified the relationships (like `Game` *has a* list of `Teams`), I think I could have expanded more on the *benefits* of the `Entity` base class. In my current draft, I mention that it reduces code duplication, but I could have explained how this specifically aids future extensibility—for example, if the client wanted to add a `Tournament` class later, it could easily inherit from `Entity` as well. Explaining the "why" behind the inheritance hierarchy more deeply would demonstrate a stronger grasp of architectural strategy.

### 5. Interpreting User Needs
**How did you interpret the user’s needs and implement them into your software design? Why is it so important to consider the user’s needs when designing?**

I interpreted the user's need for "fairness" and "clarity" as technical requirements for data integrity. For example, a user would be frustrated if they tried to create a team named "The Winners" and realized another team already had that name, causing the game to glitch. I implemented this need directly using the **Iterator Pattern**, which checks every existing name before allowing a new creation.

Considering user needs is paramount because software does not exist in a vacuum; it exists to solve a problem for a human. If the architecture is elegant but the user experience is frustrating (e.g., data gets overwritten because we didn't use a Singleton), the software has failed its primary purpose.

### 6. Future Design Strategies
**How did you approach designing software? What techniques or strategies would you use in the future to analyze and design a similar software application?**

I approached this design using a **"Constraints First" strategy**. I started by identifying what we *couldn't* do (e.g., we can't rely on the client's hardware power because they might be on a phone; we can't use Windows Server if the budget is tight). This naturally guided me toward the correct solutions (using a Linux server and a lightweight web client).

In the future, I will continue to use this strategy, but I would also incorporate more visual diagramming earlier in the process. Creating the UML diagram helped me spot the need for the `Entity` class. For future projects, I would sketch out sequence diagrams to visualize how data moves between the server and client before I even start writing the text of the design document.
