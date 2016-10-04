# scriptlanguages-php

This module provides support for using the PHP language for templating in Jahia DX as well as an example showcasing Jahia DX's template engine capability of using PHP language to render Jahia views.
This module should mostly be considered as a proof-of-concept. It is not recommended to use this code in a production
 environment.
For more information about Jahia Digital Experience Manager (DX), please go to: https://www.jahia.com/platform/digital-experience-manager

Jahia DX uses the Quercus Java PHP implementation (http://quercus.caucho.com), which might be subject to custom licensing so please check their site for more details.

## Configuration

You can specify a location (within the bundle's classpath) for a `.ini` file configure Quercus using the `jahia.scripts.php.ini.location` property that can be defined in
`digital-data-config/jahia/jahia.properties` file of your DX installation. By default, this property is set to `META-INF/quercus/php.ini`.

## Potential issues

 * Seems POST request confuses Quercus when we edit a PHP node

 * Had trouble with the working directory when trying to integrate with phpBB.

 * URLs will need to be rewritten somehow.

## Example

We have added exception handling, but it is not required, only to illustrate that Java exceptions may be code inside the PHP code.

The integration with PHP is not done through the JSR-223 API because it might need to integrate with existing PHP tools, which will have more advanced requirements. We have
integrated the Quercus PHP Java implementation that provides advanced functions. Note that PHP to Java compilation is not supported, since we only provide the open-source
implementation. You can find more information concerning the commercial implementation's advanced features here: http://www.caucho.com/products/quercus/

```php
<?php
echo "This is a PHP module ! Here below we are displaying a JCR node in PHP...<br/>";

try {
    echo $currentNode->getProperty("phpText")->getValue()->getString();
} catch (Exception $e) {
    echo 'Caught exception: ',  $e->getMessage(), "\n";
}

?>
```