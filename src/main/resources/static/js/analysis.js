// =====================================
// CareerLens Analysis Page
// =====================================

const analysis = JSON.parse(localStorage.getItem("analysis"));

if (!analysis) {
    alert("No analysis found. Please upload a resume first.");
    window.location.href = "/dashboard";
}

const scoreElement = document.getElementById("scoreValue");
const analyzeAgainBtn = document.getElementById("reanalyze");
const downloadBtn = document.getElementById("download");

// ===============================
// Animate ATS Score
// ===============================

let currentScore = 0;
let finalScore = analysis.atsScore || 0;

const scoreAnimation = setInterval(() => {

    currentScore++;

    scoreElement.innerText = currentScore + "%";

    updateCircle(currentScore);

    if (currentScore >= finalScore) {
        clearInterval(scoreAnimation);
    }

}, 20);

// ===============================
// Circular Progress
// ===============================

function updateCircle(score){

    const circle = document.querySelector(".circle");

    const degree = (score / 100) * 360;

    circle.style.background =
        `conic-gradient(
            #38bdf8 ${degree}deg,
            #1e293b ${degree}deg
        )`;

}

// ===============================
// Summary
// ===============================

const summary = document.getElementById("summary");

summary.innerHTML = `
<b>ATS Score:</b> ${analysis.atsScore}% <br><br>

<b>Matched Skills:</b>
${analysis.matchedSkills.length}
<br>

<b>Missing Skills:</b>
${analysis.missingSkills.length}
<br><br>

Improve the missing skills below to increase your ATS score.
`;

// ===============================
// Utility
// ===============================

function findGlass(title){

    const cards = document.querySelectorAll(".glass");

    for(const card of cards){

        const heading = card.querySelector("h2");

        if(heading && heading.innerText.trim() === title){

            return card;

        }

    }

    return null;

}

// ===============================
// Skill Match
// ===============================

const skillCard = findGlass("Skill Match");

if(skillCard){

    let html = "<h2>Skill Match</h2>";

    analysis.matchedSkills.forEach(skill=>{

        html += `
        <div class="progress">

            <label>${skill}</label>

            <div class="bar">

                <span style="width:100%"></span>

            </div>

        </div>
        `;

    });

    skillCard.innerHTML = html;

}

// ===============================
// Missing Skills
// ===============================

const missingCard = findGlass("Missing Skills");

if(missingCard){

    let html = "<h2>Missing Skills</h2>";

    html += '<div class="tags">';

    analysis.missingSkills.forEach(skill=>{

        html += `<span>${skill}</span>`;

    });

    html += "</div>";

    missingCard.innerHTML = html;

}

// ===============================
// Resume Improvements
// ===============================

const improveCard = findGlass("Resume Improvements");

if(improveCard){

    let html = "<h2>Resume Improvements</h2><ul>";

    analysis.missingSkills.forEach(skill=>{

        html += `<li>Learn ${skill} and include a project using it.</li>`;

    });

    html += "</ul>";

    improveCard.innerHTML = html;

}

// ===============================
// Recommended Courses
// ===============================

const courseCard = findGlass("Recommended Courses");

if(courseCard){

    let html = "<h2>Recommended Courses</h2><ul>";

    analysis.recommendedCourses.forEach(course=>{

        html += `<li>${course}</li>`;

    });

    html += "</ul>";

    courseCard.innerHTML = html;

}

// ===============================
// Recommended Projects
// ===============================

const projectCard = findGlass("Recommended Projects");

if(projectCard){

    let html = "<h2>Recommended Projects</h2><ul>";

    analysis.recommendedProjects.forEach(project=>{

        html += `<li>${project}</li>`;

    });

    html += "</ul>";

    projectCard.innerHTML = html;

}

// ===============================
// Next Step
// ===============================

const nextCard = findGlass("Next Step");

if(nextCard){

    nextCard.innerHTML = `
    <h2>Next Step</h2>

    <p>

        Learn the missing skills,
        update your resume,
        and upload it again to improve your ATS score.

    </p>
    `;

}

// ===============================
// Buttons
// ===============================

analyzeAgainBtn.addEventListener("click",()=>{

    window.location.href="/dashboard";

});

downloadBtn.addEventListener("click",()=>{

    alert("PDF Report feature coming soon.");

});

// ===============================
// Hover Animation
// ===============================

document.querySelectorAll(".glass").forEach(card=>{

    card.addEventListener("mouseenter",()=>{

        card.style.transform="translateY(-8px)";

    });

    card.addEventListener("mouseleave",()=>{

        card.style.transform="translateY(0px)";

    });

});

console.log("CareerLens Analysis Loaded Successfully");