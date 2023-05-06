Add Object Instances:

I would probably add use the remote method framework. 
import java.rmi.Remote;

I would implement this by writing a public interface in the client that returns a reference to the remote object. I would extend the interface to Remote, and move what I had for the add, divide, and echo methods to this class. 

import java.rmi.registry.Registry;
I would also need an RMI registry for my server and need to register that class, and call the methods that I already had before. 