fpstime = os.time()
frames = 0
tmpfps = 0
ballx = screenX/2
bally = screenY/2
opaddlex = screenX-30
opaddley = 0
velox = -2
veloy = -1
playerscore = 0
computerscore = 0

function load()
	g = graphics()
end
function draw()
	--your paddle
	g.rect(10, paddley, 20,100)

	--opponent paddle
	g.rect(opaddlex, opaddley, 20, 100)

	--pong ball
	g.circle(ballx, bally, 10)

	--draw player score
	g.print(playerscore, 100, 100)

	--draw computer score
	g.print(computerscore, screenX-100, 100)

	--draw fps, last things so always on top
	g.print(frames, 10,10)
end

function update()
	if os.time()-fpstime>=1000 then
		fpstime = os.time()
		frames = tmpfps
		tmpfps = 0
	else
		tmpfps=tmpfps+1
	end
	
	--work the opponent paddle
	-- 50's are to get the middle of the paddle
	if opaddley+50>=bally then
		opaddley = opaddley-1
	elseif opaddley+50<=bally then
		opaddley = opaddley+1
	end

	--work the ball
	ballx = ballx + velox
	bally = bally + veloy

	-- check collision then change ball velocity
	-- player paddle 
	-- check cx+radius for collision with paddle
	if bally>paddley and bally<paddley+100 and ballx+10>10 and ballx+10<30 then
		-- inverse velox and change veloy based on location on paddle
		velox = -velox
		if bally-paddley<50 then
			veloy = -(bally-paddley)/25
			print("top half"..(bally-paddley))
		elseif bally-paddley>=50 then
			veloy = -(50-(bally-paddley))/25
			print("bottom half"..(bally-paddley))
		end
		
	end

	if bally>opaddley and bally<opaddley+100 and ballx-10>screenX-30 and ballx-10<screenX-10 then
		velox = -velox
		if bally-opaddley<50 then
			veloy = -(bally-opaddley)/25
			print("top half")
		elseif bally-opaddley>=50 then
			veloy = -(50-(bally-opaddley))/25
			print("bottom half")
		end
	end

	-- walls of the arena, bouncing and scoring
	--bounce
	if bally<=0 then
		veloy = -veloy
	elseif bally>=screenY then
		veloy = -veloy
	end

	--scoring and reset ball
	-- change velocity based on who hit the ball
	if ballx<=0 then
		--add score to computer
		computerscore = computerscore+1
		ballx = screenX/2
		bally = screenY/2
		velox = 1
		veloy = 0

	elseif ballx>=screenX then
		--add score to player
		playerscore = playerscore+1
		ballx = screenX/2
		bally = screenY/2
		velox = -1
		veloy = 0
	end

end

function touch(x,y)
	paddley = y-50
end