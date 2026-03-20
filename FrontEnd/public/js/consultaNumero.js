let provTot = 0;
let manTot = 0;
let conTot = 0;
let ultraTot = 0;
let resTot = 0;


async function startConcultaNum() {
    const provNum = await getNumProv();
    const manNum = await getNumMan();
    const conNum = await getNumCon();
    const ultraNum = await getNumUltra();
    const resNum = await getNumRes();
    getTotal();
    getNumPes();
    getNumTer();
    getNumFreg();
    getNumComa();
    getNumCapi();
}

/************************ devolve Nº de Provocações ****************************** */
async function getNumProv() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/provocacao/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de provocações.");
        } else {
            let provNum = await response.json();

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInside",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = provNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: provNum,
                color: "#F7464A"
            }];

            var DoughnutTextInsideChart = new Chart($('#TotProv')[0].getContext('2d')).DoughnutTextInside(data, {
                responsive: false
            });
            provTot =  provNum;
        }
    })
}

/************************ devolve Nº de Mandados ****************************** */
async function getNumMan() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/mandado/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de mandados.");
        } else {
            let manNum = await response.json();
            console.log(manNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = manNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: manNum,
                color: "#E2EAE9"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotMan')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
            manTot = manNum;
        }
    })
}

/************************ devolve Nº de Consultas ****************************** */
async function getNumCon() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/consulta/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de consultas.");
        } else {
            let conNum = await response.json();
            console.log(conNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = conNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: conNum,
                color: "#D4CCC5"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotCon')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
            conTot = conNum;
        }
    })
}

/************************ devolve Nº de Ultramares ****************************** */
async function getNumUltra() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de consultas.");
        } else {
            let ultraNum = await response.json();
            console.log(ultraNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = ultraNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: ultraNum,
                color: "#949FB1"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotUltra')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
            ultraTot = ultraNum;
        }
    })
}

/************************ devolve Nº de Respostas ****************************** */
async function getNumRes() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/resposta/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de consultas.");
        } else {
            let resNum = await response.json();
            console.log(resNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = resNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: resNum,
                color: "#4D5360"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotRes')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
            resTot = resNum;
        }
    })
}

/************************ devolve Nº de Respostas ****************************** */
async function getTotal() {


    const xValues = ["Provocação", "Mandado", "Consulta", "Ultramar", "Resposta"];
    const yValues = [provTot, manTot, conTot, ultraTot, resTot];
    // const yValues = [55, 49, 44, 24, 15];
    const barColors = [
        "#b91d47",
        "#00aba9",
        "#2b5797",
        "#e8c3b9",
        "#1e7145"
    ];

    Chart.types.Pie.extend({
        name: "DoughnutTextInsideMan",
        showTooltip: function () {
            this.chart.ctx.save();
            Chart.types.Pie.prototype.showTooltip.apply(this, arguments);
            this.chart.ctx.restore();
        },
        draw: function () {
            Chart.types.Pie.prototype.draw.apply(this, arguments);

            var width = 500,
                height = 500;

            var fontSize = (height / 114).toFixed(2);
            this.chart.ctx.font = fontSize + "em Verdana";
            this.chart.ctx.textBaseline = "middle";

            var text = "",
                textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                textY = height / 2;

            this.chart.ctx.fillText(text, textX, textY);
        }
    });

    console.log('ddd ' + yValues)
    var data = [{
        value: provTot,
        color: "#F7464A"
    }, {
        value: manTot,
        color: "#E2EAE9"
    }, {
        value: conTot,
        color: "#D4CCC5"
    }, {
        value: ultraTot,
        color: "#949FB1"
    }, {
        value: resTot,
        color: "#4D5360"
    }];

    var DoughnutTextInsideMan = new Chart($('#TotalNum')[0].getContext('2d')).DoughnutTextInsideMan(data, {
        responsive: false
    });
}

/************************ devolve Nº de Pessoas ****************************** */
async function getNumPes() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/pessoa/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de consultas.");
        } else {
            let resNum = await response.json();
            console.log(resNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = resNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: resNum,
                color: "#b91d47"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotPes')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
        }
    })
}

/************************ devolve Nº de Termo ****************************** */
async function getNumTer() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de consultas.");
        } else {
            let resNum = await response.json();
            console.log(resNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = resNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: resNum,
                color: "#00aba9"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotTer')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
        }
    })
}

/************************ devolve Nº de Freguesias ****************************** */
async function getNumFreg() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de consultas.");
        } else {
            let resNum = await response.json();
            console.log(resNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = resNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: resNum,
                color: "#2b5797"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotFreg')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
        }
    })
}

/************************ devolve Nº de Comarcas ****************************** */
async function getNumComa() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de consultas.");
        } else {
            let resNum = await response.json();
            console.log(resNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = resNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: resNum,
                color: "#e8c3b9"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotComa')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
        }
    })
}

/************************ devolve Nº de Capitanias ****************************** */
async function getNumCapi() {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/capitania/count";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a numero de consultas.");
        } else {
            let resNum = await response.json();
            console.log(resNum)

            Chart.types.Doughnut.extend({
                name: "DoughnutTextInsideMan",
                showTooltip: function () {
                    this.chart.ctx.save();
                    Chart.types.Doughnut.prototype.showTooltip.apply(this, arguments);
                    this.chart.ctx.restore();
                },
                draw: function () {
                    Chart.types.Doughnut.prototype.draw.apply(this, arguments);

                    var width = this.chart.width,
                        height = this.chart.height;

                    var fontSize = (height / 114).toFixed(2);
                    this.chart.ctx.font = fontSize + "em Verdana";
                    this.chart.ctx.textBaseline = "middle";

                    var text = resNum,
                        textX = Math.round((width - this.chart.ctx.measureText(text).width) / 2),
                        textY = height / 2;

                    this.chart.ctx.fillText(text, textX, textY);
                }
            });

            var data = [{
                value: resNum,
                color: "#1e7145"
            }];

            var DoughnutTextInsideChartMan = new Chart($('#TotCapi')[0].getContext('2d')).DoughnutTextInsideMan(data, {
                responsive: false
            });
        }
    })
}