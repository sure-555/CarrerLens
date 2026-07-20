// =============================
// CareerLens Dashboard
// =============================

const chooseBtn = document.getElementById("chooseBtn");
const fileInput = document.getElementById("resumeFile");
const dropArea = document.getElementById("dropArea");
const analyzeBtn = document.getElementById("analyzeBtn");
const roleSelect = document.getElementById("jobRole");

let selectedFile = null;

// =============================
// Choose File
// =============================

chooseBtn.addEventListener("click", () => {
    fileInput.click();
});

fileInput.addEventListener("change", () => {

    if (fileInput.files.length > 0) {

        selectedFile = fileInput.files[0];

        showSelectedFile(selectedFile);

    }

});

// =============================
// Drag & Drop
// =============================

dropArea.addEventListener("dragover", (e) => {

    e.preventDefault();

    dropArea.style.borderColor = "#22c55e";

    dropArea.style.background = "rgba(34,197,94,.08)";

});

dropArea.addEventListener("dragleave", () => {

    dropArea.style.borderColor = "#38bdf8";

    dropArea.style.background = "transparent";

});

dropArea.addEventListener("drop", (e) => {

    e.preventDefault();

    dropArea.style.borderColor = "#38bdf8";

    dropArea.style.background = "transparent";

    if (e.dataTransfer.files.length > 0) {

        selectedFile = e.dataTransfer.files[0];

        showSelectedFile(selectedFile);

    }

});

// =============================
// Show Selected File
// =============================

function showSelectedFile(file){

    dropArea.innerHTML = `

        <i class="fa-solid fa-file-pdf"></i>

        <h3>${file.name}</h3>

        <p>${(file.size/1024).toFixed(1)} KB</p>

        <button id="changeFile">

            Change Resume

        </button>

    `;

    document
        .getElementById("changeFile")
        .addEventListener("click",()=>{

            fileInput.click();

        });

}

// =============================
// Analyze Resume
// =============================

analyzeBtn.addEventListener("click", async () => {

    if (!selectedFile) {
        alert("Please upload a resume.");
        return;
    }

    analyzeBtn.disabled = true;
    analyzeBtn.innerHTML = "Analyzing...";

    try {

        const formData = new FormData();
        formData.append("file", selectedFile);

        // TODO: Replace 1 with logged-in user id later
        const uploadResponse = await fetch("/api/resume/upload?userId=1", {
            method: "POST",
            body: formData
        });

        if (!uploadResponse.ok) {
            throw new Error(await uploadResponse.text());
        }

        const resume = await uploadResponse.json();

        console.log("Uploaded Resume:", resume);

        const role = roleSelect.value;

        const analysisResponse = await fetch(
            `/api/ats/analyze/${resume.id}?role=${encodeURIComponent(role)}`
        );

        if (!analysisResponse.ok) {
            throw new Error(await analysisResponse.text());
        }

        const analysis = await analysisResponse.json();

        console.log("Analysis:", analysis);

        localStorage.setItem("analysis", JSON.stringify(analysis));
        localStorage.setItem("resumeId", resume.id);
        localStorage.setItem("jobRole", role);

        window.location.href = "/analysis";

    } catch (e) {

        console.error(e);
        alert(e.message);

    } finally {

        analyzeBtn.disabled = false;
        analyzeBtn.innerHTML = "Analyze Resume";

    }

});

// =============================
// Sidebar Active Effect
// =============================

const menu=document.querySelectorAll(".sidebar li");

menu.forEach(item=>{

    item.addEventListener("click",()=>{

        menu.forEach(i=>i.classList.remove("active"));

        item.classList.add("active");

    });

});

// =============================
// Hover Animation
// =============================

document.querySelectorAll(".card").forEach(card=>{

    card.addEventListener("mouseenter",()=>{

        card.style.transform="translateY(-8px) scale(1.02)";

    });

    card.addEventListener("mouseleave",()=>{

        card.style.transform="translateY(0px) scale(1)";

    });

});

// =============================
// Fake Dashboard Statistics
// Replace with backend later
// =============================

const stats=document.querySelectorAll(".card h2");

stats.forEach(stat=>{

    if(stat.innerText.includes("%")){

        let value=0;

        const target=parseInt(stat.innerText);

        stat.innerText="0%";

        const interval=setInterval(()=>{

            value++;

            stat.innerText=value+"%";

            if(value>=target){

                clearInterval(interval);

            }

        },18);

    }

});

// =============================
// Logout
// =============================

const logout=document.querySelector(".sidebar li:last-child");

logout.addEventListener("click",()=>{

    if(confirm("Logout from CareerLens?")){

        window.location.href="/login";

    }

});

// =============================
// Load Previous Analysis
// =============================
async function loadHistory() {

    try {

        const response = await fetch("/api/ats/history");

        if (!response.ok) return;

        const history = await response.json();

        const table = document.getElementById("historyTable");
        const section = document.getElementById("analysisSection");

        table.innerHTML = "";

        if (history.length === 0) {

            section.style.display = "none";

            document.getElementById("resumeCount").innerText = "0";
            document.getElementById("highestScore").innerText = "0%";
            document.getElementById("targetRole").innerText = "-";
            document.getElementById("analysisCount").innerText = "0";

            return;
        }

        section.style.display = "block";

        // Dashboard Statistics
        const resumeNames = new Set();
        let highestScore = 0;
        let latestRole = history[0].jobRole;

        history.forEach(item => {

            resumeNames.add(item.resume.id);

            if (item.atsScore > highestScore) {
                highestScore = item.atsScore;
            }

            table.innerHTML += `
                <tr>
                    <td>${item.resume.fileName}</td>
                    <td>${item.jobRole}</td>
                    <td>${item.atsScore}%</td>
                    <td>${item.analyzedAt.replace("T"," ")}</td>
                    <td>
                        <span class="success">
                            Completed
                        </span>
                    </td>
                </tr>
            `;

        });

        document.getElementById("resumeCount").innerText = resumeNames.size;
        document.getElementById("highestScore").innerText = highestScore + "%";
        document.getElementById("targetRole").innerText = latestRole;
        document.getElementById("analysisCount").innerText = history.length;

    }

    catch(error){

        console.log(error);

    }

}

loadHistory();