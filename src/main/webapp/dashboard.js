const out = document.querySelector(".time h2");

const options = {
        method: 'GET'
};

fetch('http://worldtimeapi.org/api/ip', options)
    .then(function(response) {
            if (response.status !== 200) {
                    console.log("HTTP status", response.status);
                    return;
            }
            response.json().then(function(data) {
                    data = data.datetime;
                    const dateTime = data.split("T");
                    const date = dateTime[0].split("-");
                    const time = dateTime[1].split(":");
                    out.innerText = date[2] + ". " + date[1] + ". " + date[0] + " " + time[0] + ":" + time[1];
            });
    })
    .catch(err => console.error(err));

