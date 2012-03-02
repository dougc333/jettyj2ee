var TRUSAFE = {};

(function() {

    TRUSAFE.util = {};

    TRUSAFE.util.toAsciiValue = function(word) {
        var charArray = word.split('');
        var i, asciiArray = new Array();

        for(i = 0; i < charArray.length; i++) {
            asciiArray[i] = charArray[i].charCodeAt(0);
        }

        return asciiArray.join(',');
    };
})();