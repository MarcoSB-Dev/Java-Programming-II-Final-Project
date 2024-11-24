document.addEventListener('DOMContentLoaded', function() {
    fetchJobs();
});

/**
 * Fetches job data from the server and displays it.
 */
function fetchJobs() {
    fetch('/api/jobs')
        .then(response => response.json())
        .then(data => displayJobs(data))
        .catch(error => console.error('Error fetching jobs:', error));
}

/**
 * Displays the job data in the jobs container.
 *
 * @param {Array} jobs - An array of job objects to display.
 */
function displayJobs(jobs) {
    const jobsContainer = document.getElementById('jobs-container');
    jobsContainer.innerHTML = '';

    jobs.forEach(job => {
        const jobCard = document.createElement('div');
        jobCard.className = 'job-card';

        const companyName = document.createElement('h2');
        companyName.textContent = job.company;
        jobCard.appendChild(companyName);

        const jobTitle = document.createElement('h3');
        jobTitle.textContent = job.role;
        jobCard.appendChild(jobTitle);

        const jobDetails = createList(job.jobDetails);
        jobCard.appendChild(jobDetails);

        jobsContainer.appendChild(jobCard);
    });
}

/**
 * Creates a list of job details.
 *
 * @param {Object} details - An object containing job details with headers as keys and contents as values.
 * @returns {HTMLElement} A UL element containing the job details.
 */
function createList(details) {
    const list = document.createElement('ul');
    for (const [key, value] of Object.entries(details)) {
        const listItem = document.createElement('li');
        listItem.textContent = `${key}: ${value}`;
        list.appendChild(listItem);
    }
    return list;
}