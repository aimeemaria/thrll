# Introduction #

THRLL Programming Language
Whitepaper

Team THRLL:
Hemanth Murthy
Aimee Sanchez
Michael Seaman
Neetha Maria Sebastian
Kapil Verma

Introduction

> The THRLL language is designed to model a theme park and to simulate a crowd's behavior in the park, thereby predicting the average revenue of the theme park over a period of time. Theme parks can represent billions of dollars of investment by park owners and it can be cumbersome to predict revenue since attractions can have difficult to observe effects on the revenue. Attractions do not generate revenue directly, however the addition of a new attraction has the potential to increase attendance. This increase, in turn, is expected to augment the revenue generated from admission fees, shops and restaurants. With THRLL, we aim to provide theme park owners and creators a tool to model their theme parks and estimate the returns on their investment.

THRLL's Buzzwords

Revenue Prediction and Estimation Language :
> The THRLL programming language is intended to simulate the revenue of a theme park. The owners can take advantage of the language’s simplicity and simulation capabilities for their benefit. By simulating a theme park, including its attractions, shops, and restaurants, owners can observe how revenue is generated during a typical day of operation.  The simulation uses built-in functions to model crowd behavior, movement, and spending throughout a day in the park, thus simulating revenue generation as guests purchase admission, food, and souvenirs.  A guest's movement throughout the park is simulated using many variables, including guest energy and attraction popularity.

Theme Park Oriented :
> THRLL is specifically designed for use by theme park designers and investors. It is a language for modeling the dynamics of a park and allows the user to define elements of a theme park using the inbuilt primitives of our language. The primitives of our language are directly related to the modern theme park industry including primitivies such as lands, attractions, stores, restaurants, crowds and guests. These primitives will be familiar to users since the keywords are based on terminology used throughout the industry.

Automatic Crowd Creation / Generating:
> THRLL provides facilities to create large crowds automatically for a user defined time period. The user  can define the crowd characteristics such as energy level, spending capability, and crowd size. In addition, the crowd can be associated with a thrill tolerance, an indicator of the type of attraction the crowd is inclined to visit. The language then automatically generates the crowd by creating individual guests internally. Each guest has characteristics that correlates to the corresponding crowd characteristics, assuming a normal distribution.

Crowd Simulation:
> The crowd movement is automatically modeled by the language in the backend. This automatic modeling uses a set of rules which characterize the behavior of each guest in the crowd and their interaction with the park attractions. This is done by using the aforementioned guest objects, whose behaviors are dependent on their individual characteristics.

User Friendly :
> THRLL allows the elements of the theme park to be defined easily using a simple declarative syntax. Key  revenue calculating methods are predefined in the language and allows the user to calculate revenue in a single step. The language will also have the capability to let the user define their own revenue functions if needed. The user can also easily change some of the input parameters to the program to see how the revenue is affected. Additionally, our language provides error messages which are easily understood, helping the user debug their program.

Writeability:
> Our language stresses the writeability of programs. The language has a simple syntax, with most programming constructs provided using simple English phrases. This helps the user learn and use our language easily.

Platform Independent:
> Since the base language is Java, the language will be platform independent.

Graphical:
> While the main goal of the language is to simulate revenue, an “ideal” goal of the language is to provide a graphical simulation of the crowd movement for a day. This may or may not be included in the language.

Summary:
Intended Programmers:  (is this really a buzzword?  It's a good point to make, but I'm not sure if it's a buzzword...)
> This language is mainly for use by investors. However, it assumes that the user has basic programming skills and is familiar with programming constructs such as if-else and for loops.

# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages