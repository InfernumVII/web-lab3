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




function applyServerTime(args) {
    if (!args || !args.serverTime) return;

    const date = new Date(args.serverTime);

    const secondHand = document.querySelector('.secondHand');
    const minuteHand = document.querySelector('.minuteHand');
    const hourHand = document.querySelector('.hourHand');

    const second = date.getSeconds();
    const secondDeg = ((second / 60) * 360);
    
    if (second < 8) { 
         secondHand.style.transition = 'none';
    } else {
         secondHand.style.transition = 'all 0.4s cubic-bezier(0.1, 2.7, 0.58, 1)';
    }
    secondHand.style.transform = `rotate(${secondDeg}deg)`;

    const minute = date.getMinutes();
    const minuteDeg = ((minute / 60) * 360) + ((second / 60) * 6);
    minuteHand.style.transform = `rotate(${minuteDeg}deg)`;

    const hour = date.getHours();
    const hourDeg = ((hour / 12) * 360) + ((minute / 60) * 30);
    hourHand.style.transform = `rotate(${hourDeg}deg)`;
}

setInterval(function() {
    updateClockFromServer(); 
}, 8000);

window.onload = function() {
    updateClockFromServer();
};

window.addEventListener('load', createParticles);