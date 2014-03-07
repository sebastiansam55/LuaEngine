function draw()
	-- iterate through board
	for x=0, xlimit do
		for y=0,ylimit do
			--if square is active then draw
			if board[x][y] then
				g.rect(x*size,y*size,size,size)
			end
		end
	end
		
end

function load()
	g = graphics()
	inp = input()
	board = {}
	changed = {}
	size = 20
	xlimit =(screenX/size)-1
	ylimit = (screenY/size)-1
	-- init board of squares
	for x = 0, xlimit do
		board[x] = {}
		changed[x] = {}
		for y=0, ylimit do
			board[x][y] = false
			changed[x][y] = false
		end
	end

end

function update()
	if os.time()>=time+500 and drawing then
		print("Finished drawing")
		time = os.time()
		for x = 0, xlimit do
			changed[x] = {}
			for y = 0, ylimit do
				changed[x][y] = false
			end
		end
		drawing = false
	end
end

function touch(x,y)
	--reset time while still drawing
	drawing = true
	time = os.time()
	xloc = math.floor(x/size)
	yloc = math.floor(y/size)
	maxx = math.floor(xlimit)
	maxy = math.floor(ylimit)
	if not changed[xloc][yloc] then
		--same loc
		board[xloc][yloc] = not board[xloc][yloc]
		changed[xloc][yloc] = true
	end
	--left right
	if not changed[maxx-xloc][yloc] then
		board[maxx-xloc][yloc] = not board[maxx-xloc][yloc]
		changed[maxx-xloc][yloc] = true
	end
	if not changed[xloc][maxy-yloc] then
		board[xloc][maxy-yloc] = not board[xloc][maxy-yloc]
		changed[xloc][maxy-yloc] = true
	end
	--axis
	if not changed[maxx-xloc][maxy-yloc] then
		board[maxx-xloc][maxy-yloc] = not board[maxx-xloc][maxy-yloc]
		changed[maxx-xloc][maxy-yloc] = true
	end
end
