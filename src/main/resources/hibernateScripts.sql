USE [HibernateTrial]
GO
/****** Object:  User [hibernator]    Script Date: 2/17/2021 5:51:11 PM ******/
CREATE USER [hibernator] FOR LOGIN [hibernator] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [hibernator]
GO
/****** Object:  Table [dbo].[City]    Script Date: 2/17/2021 5:51:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[City](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[area] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[population] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[City] ON 

INSERT [dbo].[City] ([id], [area], [name], [population]) VALUES (1, N'426 km²', N'Chennai', N'70.9 lakhs')
INSERT [dbo].[City] ([id], [area], [name], [population]) VALUES (2, NULL, NULL, NULL)
INSERT [dbo].[City] ([id], [area], [name], [population]) VALUES (3, N'603.4 sqkm', N'Mumbai', N'1.84 crores')
INSERT [dbo].[City] ([id], [area], [name], [population]) VALUES (4, N'1,484 km²', N'Delhi', N'1.9 crores')
INSERT [dbo].[City] ([id], [area], [name], [population]) VALUES (5, N'206.1 km²', N'Kolkata', N'1.49 crores')
SET IDENTITY_INSERT [dbo].[City] OFF
