function createParticles() {
    const particlesContainer = document.getElementById('particles');
    const particleCount = 30;
    for (let i = 0; i < particleCount; i++) {
        const particle = document.createElement('div');
        particle.classList.add('particle');
        const size = Math.random() * 10 + 2;
        particle.style.width = `${size}px`;
        particle.style.height = `${size}px`;
        particle.style.left = `${Math.random() * 100}vw`;
        particle.style.top = `${Math.random() * 100}vh`;
        particle.style.animationDelay = `${Math.random() * 15}s`;
        

        particle.style.opacity = Math.random() * 0.5;
        
        particlesContainer.appendChild(particle);
    }
}



let pointsHistory = [];

function updatePoints(history) {
    if (history) {
        pointsHistory = history;
    }
    drawPoints();
}


function drawPoints() {
    let rInput = document.getElementById('xyR-form:r-val'); 
    let r = parseFloat(rInput.value);

    if (isNaN(r) || r <= 0) r = 1;

    const svg = document.querySelector("svg");
    
    document.querySelectorAll(".drawn-point").forEach(el => el.remove());

    pointsHistory.forEach(point => {
        let x = point.x;
        let y = point.y;
        
        const globalStep = 176 / r
        const cx = 220 + globalStep * x
        const cy = 220 - globalStep * y

        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", cx);
        circle.setAttribute("cy", cy);
        circle.setAttribute("r", "5");
        circle.setAttribute("class", "drawn-point");
        
        circle.setAttribute("fill", point.success ? "#00FF00" : "#FF0000");
        circle.setAttribute("stroke", "black");
        circle.setAttribute("stroke-width", "1");

        svg.appendChild(circle);
    });
}


function svgMouseMovement(){
    const toSVGPoint = (svg, x, y) => {
      let p = new DOMPoint(x, y);
      return p.matrixTransform(svg.getScreenCTM().inverse());
    };
    const svg = document.querySelector("svg")
    svg.addEventListener("mousemove", e => {
        let p = toSVGPoint(svg, e.clientX, e.clientY);
        const point = document.querySelector(".point")
        point.setAttribute("cx", p.x)
        point.setAttribute("cy", p.y)
    });
    svg.addEventListener("click", e => {
        const point = document.querySelector(".point")
        const cx = point.getAttribute("cx")
        const cy = point.getAttribute("cy")
        let rInput = document.getElementById('xyR-form:r-val');
        let r = parseFloat(rInput.value);
        if (isNaN(r) || r < 1 || r > 4) {
            alert("Выберите корректный R (1..4)");
            return;
        }
        console.log(cx, cy);
        const globalStep = 176 / r
        const x = (cx - 220) / globalStep
        const y = (220 - cy) / globalStep
        console.log(x, y);
        
        sendClickParams([
            {name: 'x', value: x},
            {name: 'y', value: y},
            {name: 'r', value: r}
        ]);
    });
}



window.addEventListener('load', createParticles);
window.addEventListener('load', svgMouseMovement);
document.addEventListener("DOMContentLoaded", () => {
    drawPoints();
});