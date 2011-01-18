if (top.location != location) {
	top.location.href = document.location.href ;
}

var myKey = 'ABQIAAAAC0HREQWV9s6VzAHP-J9_3RTBM8BnLg5xXwzq4WzdXy0Ua977yhRDzuXG6x1rfL2qTy79ssRgBwryAA';

/*
function fixTiles(){
	var tiles = document.getElementsByTagName('IMG');
	for (var n = 0 ; n < tiles.length ; n++ ) {
		if (tiles[n].src.match(/&src=api/)) {
			tiles[n].src = tiles[n].src.replace(/&src=api/,'');
		}
	}
	window.setTimeout("fixTiles()",500);
}

fixTiles();

*/