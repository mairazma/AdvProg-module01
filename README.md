# Module 1
## Reflection 1
I checked my source code and found three clean code principles, one secure coding practice, and one main mistake in my code.

The clean code principles are meaningful names, functions, and layout and formatting. I noticed that every variable and function is written representatively and makes it easy to understand their purpose. The functions, especially, have meaningful names even if they are long. If I check every file in this workspace, I also recognize that one file can contain many functions that are modular and easy to understand. The last one is layout and formatting. I actually love managing the indentation because I feel like proper indentation makes the code more readable and organized.

I only found one secure coding practice for now, which is input validation. Authentication, authorization, and output encoding have not been implemented yet since we just started with the main function of the web (create, edit, and delete product). Input validation is implemented when a product is created or edited, where we have to make sure that the input is valid, for example, the price should be positive.

The mistake I found in my code is that there are almost no comments. Even if the variables and functions already have meaningful names, I feel like some built-in methods still need comments to elaborate on their functionality. This is my opinion after checking my testing files again: the code cannot be fully understood at first sight.

## Reflection 2
1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?
   
    I feel that writing unit tests is a really hard thing to do, especially when it comes to having the motivation to do it. This is because we can test our code manually, but we still have to test it programmatically. In my opinion, good unit testing is not about the number of tests, but about whether the tests cover all possible cases. As long as the possible cases are tested, I think our code will be safer. The same goes for coverage: high coverage means that our tests already cover many possible scenarios. However, high coverage does not mean our code has no bugs or errors, because unit testing checks our code’s functionality, not its overall reliability or performance.


2. Suppose that after writing the `CreateProductFunctionalTest.java` along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.  

    What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

    I think that if the setup procedures and instance variables are the same, it is better to merge the tests into the `CreateProductFunctionalTest.java` file. This way, we do not have to repeat the same setup and variables, which can make the code cleaner and easier to maintain. To count the number of products in the list, we can simply add some products and make sure that the length of the list matches the number of products we created. This approach is more straightforward and avoids unnecessary duplication.

    Creating a new test file in this case will only increase redundancy and make the codebase harder to manage. It may also slightly waste memory and resources, even though the impact is small. Therefore, merging the tests into a single file with shared setup and variables can improve readability, efficiency, and overall code organization.

---

# Module 2

## Reflection
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them. 
    - Repeated constant data  
    In `ProductController.java`, there were 3 functions that returned the exact same endpoint. Previously, it was defined directly as a string (`"redirect:/product/list"`). I decided to store it in a `private final` variable and replace all occurrences with that variable. This way, the code becomes more readable and avoids repeated constant values.
    
    - Button color not contrasting enough  
    I wasn’t aware of the minimum contrast rule between a button and its background. SonarCloud flagged this issue, so I fixed it by changing the button color to a darker one to improve accessibility.
    
    - There was an empty method without a comment  
    In `EshopApplicationTest.java`, there is an empty method called `contextLoads()`. This method simply triggers the application context initialization, so there is no need to put anything inside it. However, there was no explanation about why this method exists. To make it clearer for other developers, I added a comment explaining its purpose.


2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!  
    I do think that my CI/CD workflow implementation has met the definition of Continuous Integration and Continuous Deployment. The ci.yml file automatically runs tests for every push and pull request. This helps ensure that our code works properly and remains functional with every update.  

    For the CD part, it is implemented through the service I use (Railway). This service uses a pull-based deployment mechanism. That means for every code update (push or merge to the main branch), the service will automatically attempt to build the project and deploy it if there are no issues. This way, any change to the code is automatically deployed right away.