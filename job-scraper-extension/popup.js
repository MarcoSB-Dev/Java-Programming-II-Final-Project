// add an event listener to the scrape button
document.getElementById('scrape-button').addEventListener('click', () => {
    console.log('scrape button clicked');

    // query the active tab in the current window
    chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
        console.log('active tab found:', tabs[0].id);

        // inject and execute the scrapeJobData function in the context of the active tab
        chrome.scripting.executeScript({
            target: { tabId: tabs[0].id },
            func: () => {
                /**
                 * scrapes job data from the current page.
                 */
                function scrapeJobData() {
                    console.log('scrapeJobData function called');

                    // select the job details element
                    const jobDetailsElement = document.querySelector('.jobs-box__html-content.jobs-description-content__text');
                    if (!jobDetailsElement) {
                        console.error('job details element not found');
                        return;
                    }

                    // select the role and company elements
                    const roleElement = document.querySelector('.job-details-jobs-unified-top-card__job-title h1');
                    const companyElement = document.querySelector('.job-details-jobs-unified-top-card__company-name a');

                    // extract the role and company name
                    const role = roleElement ? roleElement.innerText.trim() : 'role not found';
                    const company = companyElement ? companyElement.innerText.trim() : 'company not found';

                    console.log('role:', role);
                    console.log('company:', company);

                    // parse the job details from the spans
                    const spans = jobDetailsElement.querySelectorAll('span');
                    const parsedData = [];

                    let currentHeader = null;
                    let currentContent = '';

                    // iterate over each span to extract headers and content
                    spans.forEach((span, index) => {
                        const text = span.innerText.trim();
                        if (text.length > 0) {
                            // check if the text is a header
                            if (text.split(' ').length <= 15 && (index === 0 || spans[index - 1].innerText.trim().length === 0)) {
                                // if there is a current header and content, push it to parsedData
                                if (currentHeader && currentContent.length > 0) {
                                    parsedData.push({ header: currentHeader, content: currentContent.trim() });
                                }
                                // update the current header and reset current content
                                currentHeader = text;
                                currentContent = '';
                            } else {
                                // append the text to the current content
                                currentContent += text + ' ';
                            }
                        }
                    });

                    // push the last header and content if they exist
                    if (currentHeader && currentContent.length > 0) {
                        parsedData.push({ header: currentHeader, content: currentContent.trim() });
                    }

                    console.log('parsed job details:', parsedData);

                    // create the job data object
                    const jobData = {
                        role,
                        company,
                        details: parsedData
                    };

                    // send job data to the backend
                    fetch('http://localhost:8080/api/jobs', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(jobData) // send the jobData object directly
                    })
                        .then((response) => {
                            console.log('fetch response received');
                            return response.text().then((text) => {
                                try {
                                    const data = JSON.parse(text);
                                    console.log('success:', data);
                                } catch (error) {
                                    console.error('error parsing json:', error, text);
                                }
                            });
                        })
                        .catch((error) => {
                            console.error('error:', error);
                        });
                }

                scrapeJobData();
            }
        }, (results) => {
            if (chrome.runtime.lastError) {
                console.error('script injection failed: ', chrome.runtime.lastError);
            } else {
                console.log('script injected successfully');
            }
        });
    });
});