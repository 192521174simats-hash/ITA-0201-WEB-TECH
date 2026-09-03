/* Course data stored using JavaScript objects inside an array */
const courses = [
    { code: "WT101", name: "Web Technology", credits: 4, type: "Core" },
    { code: "DS102", name: "Data Structures", credits: 4, type: "Core" },
    { code: "DB103", name: "Database Management", credits: 3, type: "Core" },
    { code: "AI104", name: "Artificial Intelligence", credits: 4, type: "Elective" },
    { code: "CN105", name: "Computer Networks", credits: 3, type: "Core" }
];

const form = document.getElementById("registrationForm");
const summary = document.getElementById("summary");
const errorMessage = document.getElementById("errorMessage");
const resetButton = document.getElementById("resetButton");
const selectedCount = document.getElementById("selectedCount");
const courseSelection = document.getElementById("courseSelection");
const coursePreview = document.getElementById("coursePreview");
const courseTableBody = document.getElementById("courseTableBody");
const navLinks = document.getElementById("navLinks");
const menuToggle = document.getElementById("menuToggle");

/* Display course cards, table and form options dynamically */
function renderCourses() {
    coursePreview.innerHTML = "";
    courseTableBody.innerHTML = "";
    courseSelection.innerHTML = "";

    courses.forEach(course => {
        const preview = document.createElement("article");
        preview.className = "preview-card";
        preview.innerHTML = `
            <span class="preview-code">${course.code}</span>
            <h3>${course.name}</h3>
            <div class="preview-meta"><span>${course.type}</span><strong>${course.credits} Credits</strong></div>
        `;
        coursePreview.appendChild(preview);

        const row = document.createElement("tr");
        row.innerHTML = `
            <td><strong>${course.code}</strong></td>
            <td>${course.name}</td>
            <td>${course.credits}</td>
            <td>${course.type}</td>
        `;
        courseTableBody.appendChild(row);

        const label = document.createElement("label");
        label.className = "course-option";
        label.dataset.code = course.code;
        label.innerHTML = `
            <input type="checkbox" name="course" value="${course.code}">
            <span class="check">✓</span>
            <span class="code">${course.code}</span>
            <h4>${course.name}</h4>
            <div class="meta"><span>${course.type}</span><span>•</span><span>${course.credits} Credits</span></div>
        `;
        courseSelection.appendChild(label);
    });

    document.querySelectorAll('input[name="course"]').forEach(input => {
        input.addEventListener("change", updateCourseSelection);
    });
}

/* Update visual selection and selected count */
function updateCourseSelection() {
    document.querySelectorAll(".course-option").forEach(card => {
        const checkbox = card.querySelector("input");
        card.classList.toggle("selected", checkbox.checked);
    });

    const checked = document.querySelectorAll('input[name="course"]:checked').length;
    selectedCount.textContent = checked;
    document.getElementById("courseError").textContent = "";
}

/* Reusable function to calculate total selected courses and credits */
function calculateRegistration(selectedCourses) {
    let totalCredits = 0;

    selectedCourses.forEach(course => {
        totalCredits += course.credits;
    });

    return {
        totalCourses: selectedCourses.length,
        totalCredits: totalCredits
    };
}

/* Email validation */
function validateEmail(email) {
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return pattern.test(email);
}

/* Semester validation */
function validateSemester(semester) {
    const number = Number(semester);
    return Number.isInteger(number) && number >= 1 && number <= 8;
}

/* Show field-specific error */
function setFieldError(fieldId, message) {
    const group = document.getElementById(fieldId).closest(".form-group");
    const error = document.getElementById(fieldId + "Error");
    group.classList.add("invalid");
    error.textContent = message;
}

/* Clear field errors */
function clearFieldErrors() {
    document.querySelectorAll(".form-group").forEach(group => group.classList.remove("invalid"));
    document.querySelectorAll(".field-error").forEach(error => error.textContent = "");
}

/* Show general error */
function showError(message) {
    errorMessage.textContent = message;
    errorMessage.classList.add("show");
    console.log("Validation Error:", message);
}

/* Hide general error */
function clearError() {
    errorMessage.textContent = "";
    errorMessage.classList.remove("show");
}

/* Form submit event */
form.addEventListener("submit", function(event) {
    event.preventDefault();

    clearFieldErrors();
    clearError();

    const registerNumber = document.getElementById("registerNumber").value.trim();
    const studentName = document.getElementById("studentName").value.trim();
    const email = document.getElementById("email").value.trim();
    const department = document.getElementById("department").value;
    const semester = document.getElementById("semester").value;
    const selectedCodes = Array.from(
        document.querySelectorAll('input[name="course"]:checked')
    ).map(input => input.value);

    let valid = true;

    if (!registerNumber) {
        setFieldError("registerNumber", "Register Number is required.");
        valid = false;
    }

    if (!studentName) {
        setFieldError("studentName", "Student Name is required.");
        valid = false;
    }

    if (!email) {
        setFieldError("email", "Email is required.");
        valid = false;
    } else if (!validateEmail(email)) {
        setFieldError("email", "Enter a valid email address.");
        valid = false;
    }

    if (!department) {
        setFieldError("department", "Please select your department.");
        valid = false;
    }

    if (!validateSemester(semester)) {
        setFieldError("semester", "Select a semester from 1 to 8.");
        valid = false;
    }

    if (selectedCodes.length === 0) {
        document.getElementById("courseError").textContent = "Please select at least one course.";
        valid = false;
    }

    if (!valid) {
        showError("Please correct the highlighted fields and try again.");
        console.log("Form validation failed.");
        return;
    }

    /* Find selected course objects from the course array */
    const selectedCourses = courses.filter(course => selectedCodes.includes(course.code));

    console.log("Selected Courses:", selectedCourses);

    /* Reusable calculation function */
    const result = calculateRegistration(selectedCourses);

    console.log("Total Courses:", result.totalCourses);
    console.log("Total Credits:", result.totalCredits);

    /* Display registration summary without page reload */
    document.getElementById("summaryName").textContent = studentName;
    document.getElementById("summaryRegister").textContent = registerNumber;
    document.getElementById("summaryDepartment").textContent = department;
    document.getElementById("summarySemester").textContent = "Semester " + semester;
    document.getElementById("totalCourses").textContent = result.totalCourses + (result.totalCourses === 1 ? " course" : " courses");
    document.getElementById("totalCredits").textContent = result.totalCredits;

    const selectedList = document.getElementById("selectedCourses");
    selectedList.innerHTML = "";

    selectedCourses.forEach(course => {
        const item = document.createElement("li");
        item.textContent = `${course.code} - ${course.name} (${course.credits} Credits)`;
        selectedList.appendChild(item);
    });

    summary.classList.remove("hidden");
    summary.scrollIntoView({ behavior: "smooth", block: "start" });

    console.log("Registration summary displayed successfully.");
});

/* Reset form */
resetButton.addEventListener("click", function() {
    setTimeout(() => {
        clearFieldErrors();
        clearError();
        summary.classList.add("hidden");
        updateCourseSelection();
        console.log("Form has been reset.");
    }, 0);
});

/* Mobile navigation */
menuToggle.addEventListener("click", function() {
    navLinks.classList.toggle("open");
});

navLinks.querySelectorAll("a").forEach(link => {
    link.addEventListener("click", () => navLinks.classList.remove("open"));
});

/* Highlight navigation based on visible section */
const sections = document.querySelectorAll("main section[id], header[id]");
const navAnchors = document.querySelectorAll(".nav-links a");

window.addEventListener("scroll", () => {
    let current = "home";

    sections.forEach(section => {
        const top = section.offsetTop - 120;
        if (window.scrollY >= top) {
            current = section.getAttribute("id");
        }
    });

    navAnchors.forEach(anchor => {
        anchor.classList.toggle("active", anchor.getAttribute("href") === "#" + current);
    });
});

/* Initial rendering */
renderCourses();

/* Development/debugging message */
console.log("Saveetha University Course Registration Portal loaded successfully.");
console.log("Course data:", courses);
