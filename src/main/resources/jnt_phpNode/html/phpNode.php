<?php
echo "This is a PHP module ! Here below we are displaying a JCR node in PHP...<br/>";
$x = 3;
$y = 5;
$z = min($x,$y);
echo $z;
try {
    echo $currentNode->getProperty("phpText")->getValue()->getString();
} catch (Exception $e) {
    echo 'Caught exception: ',  $e->getMessage(), "\n";
}

?>