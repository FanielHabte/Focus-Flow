const completedTasks = document.querySelector(".app-completed-tasks");
const todayTasks = document.querySelector(".app-today-tasks");
const allTasks = document.querySelector(".app-all-tasks");

const btnsContainer = document.getElementById("filter-buttons");
const buttons = btnsContainer.getElementsByTagName("button");

function showCompletedTasks () {
    completedTasks.style.display= "grid"
    todayTasks.style.display = "none"
    allTasks.style.display = "none"
}

function showAllTasks () {
    allTasks.style.display = "grid"
    completedTasks.style.display = "none"
    todayTasks.style.display = "none"
}

function showTodayTasks () {
    todayTasks.style.display = "grid"
    completedTasks.style.display = "none"
    allTasks.style.display = "none"
}

function removeActiveClassFromButtons () {
    for (let i = 0; i < buttons.length; i++) {
        buttons.item(i).classList.remove("active")
    }
}

document.addEventListener("DOMContentLoaded", (event) => {
    showAllTasks();
})

btnsContainer.addEventListener("click", (e) => {
    if(e.target.tagName === "BUTTON") {
        removeActiveClassFromButtons();
        e.target.classList.add("active");

        if (e.target.textContent.trim() === "All") showAllTasks();
        else if (e.target.textContent.trim() === "Today") showTodayTasks();
        else if (e.target.textContent.trim() === "Done") showCompletedTasks();
    }
})





