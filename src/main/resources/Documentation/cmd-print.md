helloworld print
================

NAME
----
helloworld print - Print our helloworld message

SYNOPSIS
--------
>     ssh -p <port> <host> helloworld print
>      [--french]
>      [name]

DESCRIPTION
-----------
Prints "Hello world!" or optionally "Bonjour world!".

OPTIONS
-------

--french
> Translate to french.

--help

-h
> Display usage information.

name
> Display given name instead of 'world'.

ACCESS
------
Any user who has configured an SSH key.

SCRIPTING
---------
This command is intended to be used in scripts.

EXAMPLES
--------

Have the server say hi to the world.

>     $ ssh -p 29418 review.example.com helloworld print

Have the server say hi to the world in french.

>     $ ssh -p 29418 review.example.com helloworld print --french

Have the server say hi to you in french.

>     $ ssh -p 29418 review.example.com helloworld print --french \'Revi Ewer\'

SEE ALSO
--------

* [Plugin Development](dev-plugins.html)

GERRIT
------
Part of [Gerrit Code Review](index.html)
