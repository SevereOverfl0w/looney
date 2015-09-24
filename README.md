# looney
I was recently using Plex to organize my media collection, and I noticed that
my copy of "Looney Tunes Golden Collection" wasn't being processed very well.

I checked how Plex scans tv shows, and discovered it used
[TheTVDB](http://thetvdb.com/) to do it's magic. After diving into the forums
I found the [related post](https://forums.thetvdb.com/viewtopic.php?t=11566)
which mentioned my issue.

The solution was immediately clear, I needed to convert my files into the
global format so that I could enjoy my cartoons from anywhere. So was born,
Looney.

## Installation
Clone the directory, make sure you've installed leiningen. 

`git clone https://github.com/SevereOverfl0w/looney.git`

## Usage
`$ lein run -- ~/tvshows/Looney\ Tunes\ Golden\ Collection ~/tvshows/Looney\ Tunes`

## Options
Takes 2 parameters, source and destination.


## License

Copyright © 2015 Dominic Monroe

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
