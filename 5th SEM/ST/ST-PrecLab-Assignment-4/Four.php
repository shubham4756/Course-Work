<!DOCTYPE html>
<html>
    <head>
        <title>Validate Email</title>
    </head>
    <body>
        <p>The following Pages are present</p>
        <p>Covid 19</p>
        <p>SVNIT</p>
        <p>DSA</p>
        <p>DAA</p>
        <p>AIML</p>
        <form method="post" action="Four.php">
            <label for="query">Enter Here:</label>
            <input name="query" type="text">
            <button type="submit" value='Submit'>Search</button>
        </form>
        <?php
            $query = "";
            if(!empty($_POST["query"])) {
                $query = $_POST["query"];
                if($query == 'Covid 19' or $query=='covid 19') {
                    echo "<h2>Covid 19</h2>";
                    echo "<p>Covid 19 is now a global pandemic.<br>
                          Millions of people affected by it are now eagerly waiting for the situation to recover.<br> 
                          We can only hope the situation get better.</p>";
                } else if($query == 'SVNIT' or $query=='svnit') {
                    echo "<h2>SVNIT</h2>";
                    echo "<p>SVNIT is one of the most prestigeous institute in India.<br>
                         Thousands of students wish to get the admission and hope for their dream come true.<br>
                         The institute is performing excellent in every aspect since last many years.</p>";
                } else if($query == 'DSA' or $query == 'dsa' ) {
                    echo "<h2>Data Structure and Algorithm</h2>";
                    echo "<p>Data structure and algorithm is interesting subject</p>
                          <p>This subject contains the basic concept of the Data sturcture like stack, queue, tree, Graph </p>
                          <p>and Introduction to the algorithm like Serching, Sorting, greedy algorithm, Tree traversal and graph algorithm</p>";
                } else if ($query == 'DAA' or $query == 'daa') {
                    echo "<h2>Design and Algorithm Analysis</h2>";
                    echo "<p>This is subject contains Running time and space complexity of the Algorithm</br>
                          We get knowledge about the Greedy Algorithm ,Dynamic Programming and Divide and conqure<br>
                          And various algorithm analysis technique</p>";
                } else if ($query == 'aiml' or $query == 'AIML' ) {
                    echo "<h2>Artificial Intelligence and Machine Learning</h2>";
                    echo "<p>This subject is to the introduction to the Artifiacial Intelligence and Machine Learining</p>";
                } else {
                    echo "The search term DOES NOT EXIST";
                }
            } 
            else {
                echo "Please Enter a valid string";
            }
        ?>
    </body>
</html>