function draw()
	--draw the board
	for x=1,(screenX-10)/10 do
		for y=1,(screenY-10)/10 do
			if board[x][y] then
				g.rect(x*10,y*10, 10,10)
			end
		end
	end
end

function load()
	--init the board
	--over init for the sides of the baords
	board = {}
	for x=0,screenX/10 do
		board[x] = {}
		for y=0,screenY/10 do
			if math.random()>.5 then
				board[x][y]=false
			elseif math.random()<.5 then
				board[x][y]=true
			end

		end
	end	
	g = graphics()
	time = os.time()
	-- run = true
end

function update()
	if os.time()>=time+2000 then
		-- run = false
		tmplist = {}
		for x=0,screenX/10 do
			tmplist[x] = {}
			for y=0,screenY/10 do
				tmplist[x][y]=false
			end
		end	

		for x=1, (screenX-10)/10 do
			tmplist[x] = {}
			for y=1,(screenY-10)/10 do
				neigh = 0
				if board[x-1][y-1] then
					neigh = neigh+1
				end
				if board[x][y-1] then
					neigh = neigh+1
				end
				if board[x+1][y-1] then
					neigh = neigh+1
				end
				if board[x-1][y] then
					neigh = neigh+1
				end
				if board[x+1][y] then
					neigh = neigh+1
				end
				if board[x-1][y+1] then
					neigh = neigh+1
				end
				if board[x][y+1] then
					neigh = neigh+1
				end
				if board[x+1][y+1] then
					neigh = neigh+1
				end
				if board[x][y] then
					-- print("Active square: "..neigh)
					if neigh<=1 then
						tmplist[x][y]=false
					elseif neigh==2 or neigh==3 then
						tmplist[x][y] = true
					elseif neigh>=4 then
						tmplist[x][y] = false
					end
				elseif not board[x][y] then
					-- print("Inactive square: "..neigh)
					if neigh==3 then
						tmplist[x][y] = true
					end
				end
			end
		end
		board = tmplist	
		time = os.time()
		-- run = true
	end
end

function touch(touchx,touchy)
	
end