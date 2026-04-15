let completedTasks = document.querySelector(".app-completed-tasks");
let todayTasks = document.querySelector(".app-today-tasks");
let allTasks = document.querySelector(".app-all-tasks");

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