USE [Student_Manager]
GO
/****** Object:  Table [dbo].[tbl_Class]    Script Date: 1/28/2024 4:00:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Class](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[Course_ID] [int] NULL,
	[Teacher_ID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tbl_Course]    Script Date: 1/28/2024 4:00:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Course](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Begin_date] [date] NULL DEFAULT (getdate()),
	[End_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tbl_Mark]    Script Date: 1/28/2024 4:00:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Mark](
	[Student_ID] [int] NOT NULL,
	[Sub_ID] [int] NOT NULL,
	[Ex_Date] [date] NOT NULL DEFAULT (getdate()),
	[Mark] [float] NULL,
	[Status] [int] NULL DEFAULT ((3)),
	[Note] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[Student_ID] ASC,
	[Sub_ID] ASC,
	[Ex_Date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 1/28/2024 4:00:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tbl_Student]    Script Date: 1/28/2024 4:00:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Student](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaSV] [nvarchar](100) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Phone] [varchar](50) NULL,
	[Email] [varchar](100) NULL,
	[Address] [nvarchar](100) NULL,
	[DOB] [date] NULL DEFAULT ('2000/01/01'),
	[Gender] [tinyint] NULL DEFAULT ((1)),
	[img] [nvarchar](100) NULL,
	[Status] [tinyint] NULL DEFAULT ((1)),
	[Class_ID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_Subject]    Script Date: 1/28/2024 4:00:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Subject](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[accredit] [int] NULL DEFAULT ((3)),
	[price] [float] NULL DEFAULT ((500)),
	[status] [int] NULL DEFAULT ((1)),
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tbl_Teacher]    Script Date: 1/28/2024 4:00:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Teacher](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[Phone] [nvarchar](50) NULL,
	[Email] [nvarchar](100) NULL,
	[Password] [nvarchar](100) NULL,
	[Address] [nvarchar](100) NULL,
	[DOB] [date] NULL DEFAULT ('1980/01/01'),
	[Status] [tinyint] NULL DEFAULT ((1)),
	[Role_ID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[tbl_Class] ON 

INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (1, N'CT3A', 3, 2)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (2, N'CT4A', 4, 2)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (3, N'CT4B', 4, 3)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (4, N'CT5A', 5, 2)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (5, N'CT5B', 5, 3)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (7, N'CT5D', 5, 2)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (10, N'CT1A', 1, 2)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (11, N'CT5C', 5, 3)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (12, N'CT2A', 2, 2)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (13, N'CT2B', 2, 2)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (14, N'CT1B', 1, 2)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (15, N'CT3B', 3, 3)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (16, N'CT3C', 3, 6)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (17, N'CT4C', 4, 6)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (18, N'CT1C', 1, 6)
INSERT [dbo].[tbl_Class] ([ID], [Name], [Course_ID], [Teacher_ID]) VALUES (19, N'CT2C', 2, 6)
SET IDENTITY_INSERT [dbo].[tbl_Class] OFF
SET IDENTITY_INSERT [dbo].[tbl_Course] ON 

INSERT [dbo].[tbl_Course] ([ID], [Name], [Begin_date], [End_date]) VALUES (1, N'CT1', CAST(N'2016-09-05' AS Date), CAST(N'2021-05-25' AS Date))
INSERT [dbo].[tbl_Course] ([ID], [Name], [Begin_date], [End_date]) VALUES (2, N'CT2', CAST(N'2017-09-05' AS Date), CAST(N'2022-05-25' AS Date))
INSERT [dbo].[tbl_Course] ([ID], [Name], [Begin_date], [End_date]) VALUES (3, N'CT3', CAST(N'2018-09-05' AS Date), CAST(N'2023-05-25' AS Date))
INSERT [dbo].[tbl_Course] ([ID], [Name], [Begin_date], [End_date]) VALUES (4, N'CT4', CAST(N'2019-09-05' AS Date), CAST(N'2024-05-25' AS Date))
INSERT [dbo].[tbl_Course] ([ID], [Name], [Begin_date], [End_date]) VALUES (5, N'CT5', CAST(N'2020-09-05' AS Date), CAST(N'2025-05-25' AS Date))
SET IDENTITY_INSERT [dbo].[tbl_Course] OFF
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 1, CAST(N'2022-02-22' AS Date), 6, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 2, CAST(N'2022-02-22' AS Date), 8, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 3, CAST(N'2022-02-22' AS Date), 9, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 4, CAST(N'2022-02-22' AS Date), 4, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 5, CAST(N'2022-02-22' AS Date), 7, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 6, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 7, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 8, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 9, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (30, 10, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 1, CAST(N'2022-02-22' AS Date), 5, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 2, CAST(N'2022-02-22' AS Date), 6, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 3, CAST(N'2022-02-22' AS Date), 4, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 4, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 5, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 6, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 7, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 8, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 9, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (37, 10, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 1, CAST(N'2022-02-22' AS Date), 5, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 2, CAST(N'2022-02-22' AS Date), 6, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 3, CAST(N'2022-02-22' AS Date), 2, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 4, CAST(N'2022-02-22' AS Date), 3, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 5, CAST(N'2022-02-22' AS Date), 1, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 6, CAST(N'2022-02-22' AS Date), 1, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 7, CAST(N'2022-02-22' AS Date), 1, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 8, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 9, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (40, 10, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 1, CAST(N'2022-02-22' AS Date), 3, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 2, CAST(N'2022-02-22' AS Date), 1, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 3, CAST(N'2022-02-22' AS Date), 2, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 4, CAST(N'2022-02-22' AS Date), 5, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 5, CAST(N'2022-02-22' AS Date), 6, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 6, CAST(N'2022-02-22' AS Date), 7, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 7, CAST(N'2022-02-22' AS Date), 1, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 8, CAST(N'2022-02-22' AS Date), 1, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 9, CAST(N'2022-02-22' AS Date), 2, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (41, 10, CAST(N'2023-02-22' AS Date), 4, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 1, CAST(N'2022-02-22' AS Date), 6, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 2, CAST(N'2022-02-22' AS Date), 8, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 3, CAST(N'2022-02-22' AS Date), 5, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 4, CAST(N'2022-02-22' AS Date), 4, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 5, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 6, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 7, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 8, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 9, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (42, 10, CAST(N'2022-02-22' AS Date), 7, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 1, CAST(N'2022-02-22' AS Date), 5.5, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 2, CAST(N'2022-02-22' AS Date), 6, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 3, CAST(N'2022-02-22' AS Date), 6, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 4, CAST(N'2022-02-22' AS Date), 7.0999999046325684, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 5, CAST(N'2022-02-22' AS Date), 6.3000001907348633, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 6, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 7, CAST(N'2022-02-22' AS Date), 8, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 8, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 9, CAST(N'2022-02-22' AS Date), 5, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (43, 10, CAST(N'2022-02-22' AS Date), 8, 1, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 1, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 2, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 3, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 4, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 5, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 6, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 7, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 8, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 9, CAST(N'2022-02-22' AS Date), 0, 2, N'')
INSERT [dbo].[tbl_Mark] ([Student_ID], [Sub_ID], [Ex_Date], [Mark], [Status], [Note]) VALUES (47, 10, CAST(N'2022-02-22' AS Date), 0, 2, N'')
SET IDENTITY_INSERT [dbo].[tbl_Role] ON 

INSERT [dbo].[tbl_Role] ([ID], [Name]) VALUES (1, N'Admin')
INSERT [dbo].[tbl_Role] ([ID], [Name]) VALUES (2, N'NV QLSV')
INSERT [dbo].[tbl_Role] ([ID], [Name]) VALUES (3, N'Giảng viên')
INSERT [dbo].[tbl_Role] ([ID], [Name]) VALUES (4, N'Giảng viên thực tập')
SET IDENTITY_INSERT [dbo].[tbl_Role] OFF
SET IDENTITY_INSERT [dbo].[tbl_Student] ON 

INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (28, N'CT040123', N'Lê Văn Hải', N'01804288', N'Hai@gmail.com', N'HN', CAST(N'2004-01-28' AS Date), 1, N'', 1, 2)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (30, N'CT030101', N'Đoạn Văn Trường', N'975645678', N'Truong@gmail.com', N'HN', CAST(N'2004-05-24' AS Date), 1, N'', 1, 1)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (37, N'CT050307', N'Ngô Thị Ánh', N'010023621', N'Anh@gmail.com', N'HN', CAST(N'2005-01-18' AS Date), 2, N'', 1, 11)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (40, N'CT050402', N'Lò Văn Đến', N'025200594', N'Den@gmail.com', N'HN', CAST(N'2005-12-02' AS Date), 1, N'', 1, 7)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (41, N'CT050403', N'Đoạn Văn Hoàng', N'0175139232', N'ironmarine12@gmail.com', N'HN', CAST(N'2005-05-01' AS Date), 1, N'', 1, 7)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (42, N'CT050404', N'Hải Lê Hoàng A', N'0383426645', N'Hoang@gmail.com', N'HN', CAST(N'2005-07-09' AS Date), 1, N'', 1, 7)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (43, N'CT050405', N'Nguyễn Thị Duy Loan', N'0408917232', N'Loan1234@gmail.com', N'HN', CAST(N'2005-06-06' AS Date), 2, N'', 1, 7)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (46, N'CT020101', N'Nguyễn Anh Tuấn', N'457458346', N'at@gmail.com', N'HN', CAST(N'2000-01-01' AS Date), 1, N'', 1, 12)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (47, N'CT010101', N'Nguyễn Thị Hoa', N'1234432234', N'Hoa@gmail.com', N'HN', CAST(N'2000-01-01' AS Date), 2, N'', 1, 10)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (48, N'CT040211', N'Nguyễn văn B', N'0987657456', N'VB@gmail.com', N'ND', CAST(N'2000-01-01' AS Date), 1, N'', 1, 3)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (49, N'CT050424', N'Nguyễn Văn Trường', N'0965245367', N'VT@gmail.com', N'HP', CAST(N'2002-04-01' AS Date), 1, N'', 1, 4)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (50, N'CT050312', N'Trần Quang Hải', N'0768564213', N'QH123@gmail.com', N'ĐN', CAST(N'2002-02-05' AS Date), 1, N'', 1, 11)
INSERT [dbo].[tbl_Student] ([ID], [MaSV], [Name], [Phone], [Email], [Address], [DOB], [Gender], [img], [Status], [Class_ID]) VALUES (51, N'CT020321', N'Đào Minh Trí', N'0876243123', N'MT@gmail.com', N'HP', CAST(N'2001-08-01' AS Date), 1, N'', 1, 19)
SET IDENTITY_INSERT [dbo].[tbl_Student] OFF
SET IDENTITY_INSERT [dbo].[tbl_Subject] ON 

INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (1, N'Công nghệ mạng máy tính', 2, 800000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (2, N'Lập trình căn bản', 3, 1200000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (3, N'Vật lý đại cương A1', 3, 1200000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (4, N'Toán cao cấp A1', 3, 1200000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (5, N'Triết học Mác Lênin', 2, 800000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (6, N'Tư tưởng Hồ Chí Minh', 2, 800000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (7, N'Tin học đại cương', 3, 1200000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (8, N'Tiếng Anh chuyên ngành', 4, 1600000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (9, N'Tiếng Anh 1', 3, 1200000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (10, N'Toán cao cấp A2', 3, 1200000, 1)
INSERT [dbo].[tbl_Subject] ([ID], [Name], [accredit], [price], [status]) VALUES (15, N'Tiếng Anh 3', 4, 1600000, 1)
SET IDENTITY_INSERT [dbo].[tbl_Subject] OFF
SET IDENTITY_INSERT [dbo].[tbl_Teacher] ON 

INSERT [dbo].[tbl_Teacher] ([ID], [Name], [Phone], [Email], [Password], [Address], [DOB], [Status], [Role_ID]) VALUES (1, N'Trần Thu T', N'0961114104', N't@gmail.com', N'123456', N'HN', CAST(N'1999-11-12' AS Date), 1, 2)
INSERT [dbo].[tbl_Teacher] ([ID], [Name], [Phone], [Email], [Password], [Address], [DOB], [Status], [Role_ID]) VALUES (2, N'Trần Văn A', N'0886860224', N'admin@gmail.com', N'123456', N'HN', CAST(N'1995-02-24' AS Date), 1, 1)
INSERT [dbo].[tbl_Teacher] ([ID], [Name], [Phone], [Email], [Password], [Address], [DOB], [Status], [Role_ID]) VALUES (3, N'Nguyễn Thị B', N'0987152452', N'b@gmail.com', N'123456', N'BN', CAST(N'1997-01-12' AS Date), 1, 3)
INSERT [dbo].[tbl_Teacher] ([ID], [Name], [Phone], [Email], [Password], [Address], [DOB], [Status], [Role_ID]) VALUES (4, N'Nguyễn Văn C', N'0961117584', N'c@gmail.com', N'123456', N'HN', CAST(N'1998-11-24' AS Date), 1, 4)
INSERT [dbo].[tbl_Teacher] ([ID], [Name], [Phone], [Email], [Password], [Address], [DOB], [Status], [Role_ID]) VALUES (5, N'Nguyễn Văn D', N'0961114888', N'd@gmail.com', N'123456', N'hp', CAST(N'2002-11-30' AS Date), 1, 2)
INSERT [dbo].[tbl_Teacher] ([ID], [Name], [Phone], [Email], [Password], [Address], [DOB], [Status], [Role_ID]) VALUES (6, N'Nguyễn Văn E', N'4567246423', N'e@gmail.com', N'123456', N'hn', CAST(N'2000-01-01' AS Date), 2, 3)
SET IDENTITY_INSERT [dbo].[tbl_Teacher] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__tbl_Stud__2725081B1D80AE70]    Script Date: 1/28/2024 4:00:47 PM ******/
ALTER TABLE [dbo].[tbl_Student] ADD UNIQUE NONCLUSTERED 
(
	[MaSV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__tbl_Stud__5C7E359EE15CC4A0]    Script Date: 1/28/2024 4:00:47 PM ******/
ALTER TABLE [dbo].[tbl_Student] ADD UNIQUE NONCLUSTERED 
(
	[Phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__tbl_Stud__A9D10534080A1886]    Script Date: 1/28/2024 4:00:47 PM ******/
ALTER TABLE [dbo].[tbl_Student] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__tbl_Teac__5C7E359E1E55BB4D]    Script Date: 1/28/2024 4:00:47 PM ******/
ALTER TABLE [dbo].[tbl_Teacher] ADD UNIQUE NONCLUSTERED 
(
	[Phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__tbl_Teac__A9D10534E20E2FD7]    Script Date: 1/28/2024 4:00:47 PM ******/
ALTER TABLE [dbo].[tbl_Teacher] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tbl_Class]  WITH CHECK ADD FOREIGN KEY([Course_ID])
REFERENCES [dbo].[tbl_Course] ([ID])
GO
ALTER TABLE [dbo].[tbl_Class]  WITH CHECK ADD FOREIGN KEY([Teacher_ID])
REFERENCES [dbo].[tbl_Teacher] ([ID])
GO
ALTER TABLE [dbo].[tbl_Mark]  WITH CHECK ADD FOREIGN KEY([Student_ID])
REFERENCES [dbo].[tbl_Student] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tbl_Mark]  WITH CHECK ADD FOREIGN KEY([Sub_ID])
REFERENCES [dbo].[tbl_Subject] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tbl_Student]  WITH CHECK ADD FOREIGN KEY([Class_ID])
REFERENCES [dbo].[tbl_Class] ([ID])
GO
ALTER TABLE [dbo].[tbl_Teacher]  WITH CHECK ADD FOREIGN KEY([Role_ID])
REFERENCES [dbo].[tbl_Role] ([ID])
GO
