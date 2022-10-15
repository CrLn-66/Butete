//board
var BlockSize = 30;
var rows = 25;
var cols = 25;
var board;
var context;

//head of butete
var snakeX = BlockSize * 4;
var snakeY = BlockSize * 4;
var gap = 5;
//food (tae)
var foodX;
var foodY;
var score = 0;
//speed
var velocityX = 0;
var velocityY = 0;
var speed = 175;
var gameOver = false;
var highScore;

//body
var snakeBody = [];
window.onload = function(){
  board = document.getElementById("board");
  board.height = rows * BlockSize;
  board.width = cols * BlockSize;
  context = board.getContext("2d");//drawing on board
  placeTae();
  document.addEventListener("keyup", changeDirection);
 // update();
 var cookie = getCookie("hscore");
 if(cookie == "" || cookie == null){
   document.cookie = "hscore=0";
   highScore = 0;
 }else{
   highScore = cookie;
 }
 update();
//resetVar();
}

function resetVar(){
  gameOver = false;
  speed = 175;
  gap = 5;
  score = 0;
  snakeX =  BlockSize * 4;
  snakeY =  BlockSize * 4;
  velocityX = 0;
  velocityY = 0;
  snakeBody = [];
  placeTae();
}

function update(){
  if(gameOver){
    if(score > highScore){
      highScore = score;
      document.cookie = "hscore="+highScore;
    }
    resetVar();
    //update();
  }
  
  //board
  context.fillStyle="black";
  context.fillRect(0,0,board.width,board.height);
  
  //food
  context.fillStyle = "#964B00";
  context.fillRect(foodX, foodY, BlockSize, BlockSize);
  
  if (snakeX == foodX && snakeY == foodY){
    snakeBody.push([foodX, foodY]);
    score += 1;
    placeTae();
  }
 
  for(let i = snakeBody.length-1; i> 0;i--){
    snakeBody[i]= snakeBody[i-1];
  }
  if(snakeBody.length){
    snakeBody[0] = [snakeX, snakeY];
  }
   //butete head
    context.fillStyle="yellow";
  
// content.fillText(snakeY, 41, 50);
  snakeX += velocityX * BlockSize;
  snakeY += velocityY * BlockSize;
  context.fillRect(snakeX, snakeY, BlockSize, BlockSize);
  for (let i =0; i < snakeBody.length;i++) {
    context.fillRect(snakeBody[i][0],snakeBody[i][1], BlockSize, BlockSize);
  }
  context.font = "40px Arial";
 context.fillText(score, 10, 35);
 context.font = "25px Arial";
 context.fillText("High Score : "+highScore, 10, 70);
 if(score == gap){
   speed -= 15;
   gap += 5;
 }
  //is GameOver
  if(snakeX < 0 || snakeX > (cols * BlockSize)-25 || snakeY < 0 || snakeY > (rows * BlockSize)-25){
    gameOver = true;
    alert("Idiot!! why did you hit the wall!?");
    //resetVar();
  }
  
  for (let i = 0; i < snakeBody.length; i++) {
    if(snakeX == snakeBody[i][0] && snakeY == snakeBody[i][1]){
      gameOver = true;
      alert("Greedy bitch!!");
      
    }
  }
  window.setTimeout(update, speed);
}

function placeTae(){
  foodX = Math.floor(Math.random()*cols) * BlockSize;
  foodY = Math.floor(Math.random()*rows) * BlockSize;
  
}

function changeDirection(e){
  console.log(e);
     if (e.code == "ArrowUp") {
       action('up');
     }
    else if (e.code == "ArrowDown") {
       action('down');
     }
    else if (e.code == "ArrowLeft") {
       action('left');
     }
    else if (e.code == "ArrowRight") {
       action('right');
     }
}

function action(f){
   if (f == "up" && velocityY != 1){
    velocityX =0;
    velocityY = -1;
  }
 else if (f == "down" && velocityY != -1) {
     velocityX = 0;
     velocityY = 1;
   }
 else if (f == "left" && velocityX != 1) {
     velocityX = -1;
     velocityY = 0;
   }
 else if (f == "right" && velocityX != -1) {
      velocityX = 1;
      velocityY = 0;
    }
}
function getCookie(username) {
  let name = username + "=";
  let spli = document.cookie.split(';');
  for(var j = 0; j < spli.length; j++) {
    let char = spli[j];
    while (char.charAt(0) == ' ') {
      char = char.substring(1);
    }
    if (char.indexOf(name) == 0) {
      return char.substring(name.length, char.length);
    }
  }
  return "";
}
