Sistemes Oberts URV 2014 First Assignment. URL Shortener

GoShort! A handy way to shorten your URLs
=======


Description
-------------------------------------------------------------
 - a NetBeans web server project,
 - based on Java Servlet apps,
 - architecture lying on Model-View-Controller design pattern,
 - supported by Glassfish server, and
 - Embedded Derby as database, using JPA as persistence API,
 - and eclipseLink as automated table generation.

following at all stages the plain description given by professors
 - Jordi Pujol and Marc Sánchez.


Setting up the project
-------------------------------------------------------------

In order to execute this project, just clone the project into your
Workspace, open it using NetBeans (recommended) and build it.

Then simply execute the web application. It should be deployed without
any inconvenient.

NOTE that JPA, JTA and EclipseLink will generate automatically the
required database resources within the "sample" database provided by
default on NetBeans, using the parameters set on META-INF/persistence.xml.

If you experience any issue, check that your system parameters
match the ones specified in persistence.xml and please let us know about it.


----------------------------------------------------------

Inner logic based on implemented design patterns:
 - MVC (Mandatory)
 - Command (Mandatory)
 - Adapter
 - Factory
 - DAO

And other design decisions such as:
 - Ease-of-use as a learning resource
 - Portability, robustness of the code
 - Layered and flexible architecture:
    - View
    - Controller
    - Handlers
    - DAOs
    - Persistence
 - Connection Pooling
 - and many more!

----------------------------------------------------------

Main View States
======

![alt tag](http://i.imgur.com/5r7ZKlc.png)



Architecture Overview
======

![alt tag](http://i.imgur.com/0hQJICx.png)



UML Plain Class Diagram
======

![alt tag](http://i.imgur.com/Y4EGKDJ.png)


Contact us: 
 - Javi Garcia:          javigd.dtk@gmail.com
 - Maximiliano González: gonzalezcorvini@gmail.com
