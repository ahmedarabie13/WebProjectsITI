var i = 0;

function sum() {
    var sum = 0;
    for(var i = 0 ; i < 1000000 ; i++){
        sum = sum + i;
    }
    postMessage(sum);
}

sum();