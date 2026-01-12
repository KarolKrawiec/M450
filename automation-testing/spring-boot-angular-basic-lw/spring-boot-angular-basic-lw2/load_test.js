const http = require('http');

const options = {
    hostname: 'localhost',
    port: 8081,
    path: '/students',
    method: 'GET',
};

let successCount = 0;
let failCount = 0;
const totalRequests = 1000;
let completedRequests = 0;

const startTime = Date.now();

console.log(`Starting load test with ${totalRequests} requests to port ${options.port}...`);

for (let i = 0; i < totalRequests; i++) {
    const req = http.request(options, (res) => {
        res.on('data', () => { });
        res.on('end', () => {
            if (res.statusCode === 200) {
                successCount++;
            } else {
                console.log(`Failed with status: ${res.statusCode}`);
                failCount++;
            }
            checkDone();
        });
    });

    req.on('error', (e) => {
        console.error(`Problem with request: ${e.message}`);
        failCount++;
        checkDone();
    });

    req.end();
}

function checkDone() {
    completedRequests++;
    if (completedRequests === totalRequests) {
        const duration = (Date.now() - startTime) / 1000;
        console.log(`Load test completed in ${duration} seconds.`);
        console.log(`Successful: ${successCount}`);
        console.log(`Failed: ${failCount}`);
        console.log(`RPS: ${totalRequests / duration}`);
    }
}
