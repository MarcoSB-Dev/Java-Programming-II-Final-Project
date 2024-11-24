// function scrapeJobData() {
//     console.log('scrapeJobData function called');

//     const jobDetailsElement = document.getElementById('job-details');
//     if (!jobDetailsElement) {
//         console.error('Job details element not found');
//         return;
//     }

//     const spans = jobDetailsElement.querySelectorAll('span');
//     const parsedData = [];

//     for (let i = 0; i < spans.length; i++) {
//         const headerCandidate = spans[i].innerText.trim();
//         const nextSpan = spans[i + 1]?.innerText.trim();

//         if (
//             headerCandidate.length > 0 &&
//             headerCandidate.split(' ').length <= 15 &&
//             nextSpan &&
//             nextSpan.split(' ').length > 10
//         ) {
//             const entry = {
//                 header: headerCandidate,
//                 content: nextSpan,
//             };

//             const isDuplicate = parsedData.some(
//                 (item) =>
//                     item.header === entry.header && item.content === entry.content
//             );

//             if (!isDuplicate) {
//                 parsedData.push(entry);
//             }
//         }
//     }

//     console.log('Parsed job details:', parsedData);

//     fetch('http://localhost:8080/api/jobs', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({ jobData: parsedData })
//     })
//         .then((response) => {
//             console.log('Fetch response received');
//             return response.text().then((text) => {
//                 try {
//                     const data = JSON.parse(text);
//                     console.log('Success:', data);
//                 } catch (error) {
//                     console.error('Error parsing JSON:', error, text);
//                 }
//             });
//         })
//         .catch((error) => {
//             console.error('Error:', error);
//         });
// }