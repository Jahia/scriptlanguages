# scriptlanguages-php

This module provides support for using the PHP language for templating in Jahia DX as well as an example showcasing Jahia DX's template engine capability of using PHP language to render Jahia views.

Jahia DX uses the Quercus Java PHP implementation (http://quercus.caucho.com), which might be subject to custom licensing so please check their site for more details.

## Configuration

You can specify a location (within the bundle's classpath) for a `.ini` file configure Quercus using the `jahia.scripts.php.ini.location` property that can be defined in
`digital-data-config/jahia/jahia.properties` file of your DX installation. By default, this property is set to `META-INF/quercus/php.ini`.

## Potential issues

 * Seems POST request confuses Quercus when we edit a PHP node

 * Had trouble with the working directory when trying to integrate with phpBB.

 * URLs will need to be rewritten somehow.
