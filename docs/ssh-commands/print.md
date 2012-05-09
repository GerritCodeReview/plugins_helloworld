helloworld print
================

NAME
----
helloworld print - Print our helloworld message

SYNOPSIS
--------
>     ssh -p <port> <host> helloworld print
>      [--french]

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

ACCESS
------
Any user who has configured an SSH key.

SCRIPTING
---------
This command is intended to be used in scripts.

EXAMPLES
--------

Have the server say hi to you.

>     $ ssh -p 29418 review.example.com helloworld print

Have the server say hi to you in french.

>     $ ssh -p 29418 review.example.com helloworld print --french

SEE ALSO
--------

* [Plugin Development](dev-plugins.html)

GERRIT
------
Part of [Gerrit Code Review](index.html)
