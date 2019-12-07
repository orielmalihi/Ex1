# About this project 
>this project represent a way to **calculate** and **draw** functions like Monom, Polynom, and Complex Functions.


----------


## Types of functions

 - **Monom :** this function is of shape **a*x^b**, where **a** is a real number and **b** is an integer (summed a none negative).
 - **Polynom:** this function represents a sum of **n** Monoms, **n** is bigger or equal to 0.
 - **Complex Function:** this function is of type **y=g(f1(x), f2(x))**, where both **f1** and **f2** are functions (Monom/Polynom/Complex Fubction) ,  **y** and **x** are real numbers and **g** is an **operation**
 

> **Operations:**

 - **Plus**: f1 + f2
 - **Multiply**: f1 * f2
 - **Divide**: f1 / f2
 - **Max**: calculates f1(x) and f2(x) for a given x and return the max between them.
 - **Min**: the same as Max but retrun the min between them.
 - **Complex**: f1(f2(x))

plus, multiply, divide, max, min, complex(f1(f2(x))).

***Examples of Functions :***

 -  **Monom** :  3x^2 
 -  **Polynom** : -5 + x^2 + x^3 - x^5
 -  **Complex Function** : ( 2x * ( 3 + x ) ) / x^7
 


----------
## Usable methods
 - **F**: to calculate f(x) for a given x.
 - **Derivative**: to return a derivative of Polynom (and Monom which is a private case of Polynom).
 - **Area**: Compute a Riman's integral from x0 to x1 in epsilon steps for a Polynom.
 - **Root**: Compute a value x' of a Polynom (x0<=x'<=x1) for which |f(x')| < eps. assuming (f(x0)*f(x1)<=0.
 - **Equals**: Test if two functions are logically equals.
 - **Draw**: to draw a function.
 - **Save/Load**: to save/load a collection of functions to/from your pc.

