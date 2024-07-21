document.addEventListener('mousemove', (e) => {
    const pawPrints = document.querySelectorAll('.paw-print');
    pawPrints.forEach((paw) => {
        paw.style.left = e.pageX + 'px';
        paw.style.top = e.pageY + 'px';
        paw.style.opacity = 1;
        paw.addEventListener('animationend', () => {
            paw.style.opacity = 0;
        });
    });
});