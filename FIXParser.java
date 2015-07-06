package fix.parser;

public class FIXParser {
	
	String fixMsg = "";
	String tagField, nextTagField, dataField;
	String[][] tagFields;
	int start, end;
	static int rowPos = 0, colPos = 0;
	int code;
	
	public FIXParser(String fixMsg) {
		this.fixMsg = fixMsg;
		tagFields = new String[30][5];
	}
	
	public String[][] parse() {
			// 1, Account
			tagField = new String("1=");
			start = fixMsg.indexOf(tagField);
			nextTagField = new String("21=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "Account";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Account if destination firm requires.";
			colPos = 0;
			// 21, HandlInst
			tagField = new String("21=");
			start = end;
			nextTagField = new String("55=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "HandlInst";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Private AutoEx, no broker intervention";
			colPos = 0;
			// 55, Symbol
			tagField = new String("55=");
			start = end;
			nextTagField = new String("59=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "Symbol";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Ticker symbol";
			colPos = 0;
			// 59, TimeInForce
			tagField = new String("59=");
			start = end;
			nextTagField = new String("54=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "TimeInForce";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "0 = Day (Default); 3 = Immediate or Cancel; 4 = Fill or Kill";
			colPos = 0;
			// 54, Side
			tagField = new String("54=");
			start = end;
			nextTagField = new String("38=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "Side";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "1 = Buy; 2 = Sell; 5 = Short Sell";
			colPos = 0;
			// 38, OrderQty
			tagField = new String("38=");
			start = end;
			nextTagField = new String("40=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "OrderQty";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Number of shares";
			colPos = 0;
			// 40, OrderType
			tagField = new String("40=");
			start = end;
			nextTagField = new String("44=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "OrderType";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "2 = Limit";
			colPos = 0;
			// 44, Price
			tagField = new String("44=");
			start = end;
			nextTagField = new String("76=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "Price";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Price per share (Limit order price)";
			colPos = 0;
			// 76, ExecBroker
			tagField = new String("76=");
			start = end;
			nextTagField = new String("6751=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "ExecBroker";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Used for firm identification in third-party transactions. If no third party, then the ID of the primary firm.";
			colPos = 0;
			// 6751, UMIRUserId
			tagField = new String("6751=");
			start = end;
			nextTagField = new String("6750=");
			if(fixMsg.contains(nextTagField) ==  true) {	// Next tag is 6750
				end = fixMsg.indexOf(nextTagField, start);
				dataField = fixMsg.substring(start + tagField.length(), end);
				tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
				tagFields[rowPos][colPos++] = "UMIRUserId";
				tagFields[rowPos][colPos++] = dataField;
				tagFields[rowPos++][colPos] = "The trading system’s user id for the trader.";
				colPos = 0;
				// 6750, UMIRAccountTypeId
				tagField = new String("6750=");
				start = end;
				nextTagField = new String("6820=");
				end = fixMsg.indexOf(nextTagField, start);
				dataField = fixMsg.substring(start + tagField.length(), end);
				tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
				tagFields[rowPos][colPos++] = "UMIRAccountTypeId";
				tagFields[rowPos][colPos++] = dataField;
				tagFields[rowPos++][colPos] = "NC = Non-Client (Default); CL = Client; IN = Inventory; MP = ME Pro Order;"
												+ "ST = Equities Specialist; OT = Options Market Maker; OF = Options Firm Account";
				colPos = 0;
			} else {	// Next tag is 7713
				nextTagField = new String("7713=");
				end = fixMsg.indexOf(nextTagField, start);
				dataField = fixMsg.substring(start + tagField.length(), end);
				tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
				tagFields[rowPos][colPos++] = "UMIRUserId";
				tagFields[rowPos][colPos++] = dataField;
				tagFields[rowPos++][colPos] = "The trading system’s user id for the trader.";
				colPos = 0;
				// 7713, Unknown
				tagField = new String("7713=");
				start = end;
				nextTagField = new String("7714=");
				end = fixMsg.indexOf(nextTagField, start);
				dataField = fixMsg.substring(start + tagField.length(), end);
				tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
				tagFields[rowPos][colPos++] = "N/A";
				tagFields[rowPos][colPos++] = dataField;
				tagFields[rowPos++][colPos] = "N/A";
				colPos = 0;
				// 7714, Unknown
				tagField = new String("7714=");
				start = end;
				nextTagField = new String("6820=");
				end = fixMsg.indexOf(nextTagField, start);
				dataField = fixMsg.substring(start + tagField.length(), end);
				tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
				tagFields[rowPos][colPos++] = "N/A";
				tagFields[rowPos][colPos++] = dataField;
				tagFields[rowPos++][colPos] = "N/A";
				colPos = 0;
			}
			// 6820, Protection
			tagField = new String("6820=");
			start = end;
			nextTagField = new String("6888=");
			if(fixMsg.contains(nextTagField) ==  true) {	// Next tag is 6888
				end = fixMsg.indexOf(nextTagField, start);
				dataField = fixMsg.substring(start + tagField.length(), end);
				tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
				tagFields[rowPos][colPos++] = "Protection";
				tagFields[rowPos][colPos++] = dataField;
				tagFields[rowPos++][colPos] = "Y = Protection On; N = Protection By-pass";
				colPos = 0;
				// 6888, PostOnMarket
				tagField = new String("6888=");
				start = end;
				nextTagField = new String("60=");
				end = fixMsg.indexOf(nextTagField, start);
				dataField = fixMsg.substring(start + tagField.length(), end);
				tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
				tagFields[rowPos][colPos++] = "PostOnMarket";
				tagFields[rowPos][colPos++] = dataField;
				tagFields[rowPos++][colPos] = "OMGA = Post on Omega; LYNX = Post on Lynx";
				colPos = 0;
			} else {	// Next tag is 60
				nextTagField = new String("60=");
				end = fixMsg.indexOf(nextTagField, start);
				dataField = fixMsg.substring(start + tagField.length(), end);
				tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
				tagFields[rowPos][colPos++] = "Protection";
				tagFields[rowPos][colPos++] = dataField;
				tagFields[rowPos++][colPos] = "Y = Protection On; N = Protection By-pass";
				colPos = 0;
			}
			// 60, TransactTime
			tagField = new String("60=");
			start = end;
			nextTagField = new String("37=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "TransactTime";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "UTC Time";
			colPos = 0;
			// 37, OrderID
			tagField = new String("37=");
			start = end;
			nextTagField = new String("11=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "OrderID";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "The client’s OrderID";
			colPos = 0;
			// 11, ClOrdID
			tagField = new String("11=");
			start = end;
			nextTagField = new String("17=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "ClOrdID";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Unique order ID of Order Cancel Request or Order Cancel/Replace Request";
			colPos = 0;
			// 17, ExecID
			tagField = new String("17=");
			start = end;
			nextTagField = new String("20=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "ExecID";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Omega assigned execution identifier";
			colPos = 0;
			// 20, ExecTransType
			tagField = new String("20=");
			start = end;
			nextTagField = new String("150=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "ExecTransType";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "0 = New; 1 = Cancel; 2 = Correct; 3 = Status";
			colPos = 0;
			// 150, ExecType
			tagField = new String("150=");
			start = end;
			nextTagField = new String("39=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "ExecType";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "0 = New; 1 = Partially filled; 2 = Filled; 3 = Done for day; 4 = Canceled; 5 = Replaced" +
												"6 = Pending cancel/replace; 8 = Rejected; 9 = Suspended; D = Restatement; E = Pending Replace";
			colPos = 0;
			// 39, OrderStatus
			tagField = new String("39=");
			start = end;
			nextTagField = new String("151=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "OrderStatus";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "0 = New; 1 = Partially filled; 2 = Filled; 3 = Done for day; 4 = Canceled; 5 = Replaced" +
												"6 = Pending cancel/replace; 8 = Rejected; 9 = Suspended";
			colPos = 0;
			// 151, LeavesQty
			tagField = new String("151=");
			start = end;
			nextTagField = new String("14=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "LeavesQty";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Shares open for further execution.";
			colPos = 0;
			// 14, CumQty
			tagField = new String("14=");
			start = end;
			nextTagField = new String("6=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "CumQty";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Cumulative Qty. Total number of shares filled.";
			colPos = 0;
			// 6, AvgPx
			tagField = new String("6=");
			start = end;
			nextTagField = new String("31=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "AvgPx";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Average price of CumQty shares.";
			colPos = 0;
			// 31, LastPx
			tagField = new String("31=");
			start = end;
			nextTagField = new String("32=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "LastPx";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Price of last fill.";
			colPos = 0;
			// 32, LastShares
			tagField = new String("32=");
			start = end;
			nextTagField = new String("58=");
			end = fixMsg.indexOf(nextTagField, start);
			dataField = fixMsg.substring(start + tagField.length(), end);
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "LastShares";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Qty of shares last bought / sold on this order.";
			colPos = 0;
			// 58, Text
			tagField = new String("58=");
			start = end;
			dataField = fixMsg.substring(start + tagField.length());
			tagFields[rowPos][colPos++] = tagField.substring(0, tagField.indexOf("="));
			tagFields[rowPos][colPos++] = "Text";
			tagFields[rowPos][colPos++] = dataField;
			tagFields[rowPos++][colPos] = "Free format character string.";
			colPos = 0;
			return tagFields;	
	}
	
	
	public static void main(String[] args) {
		FIXParser tester = new FIXParser("1=XG21=155=MBT59=054=138=100040=244=10.00076=0336751=ITS001E6750=NC6820=N6888=OMGA60=20150521-11:45:1837=20150521-111=MCHENRY8hur1417=001A120=0150=039=0151=100014=06=0.00031=0.00032=058=New Order ACK");
		String[][] testTable = tester.parse();
		for(int i = 0; i < rowPos; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.println(testTable[i][j]);
			}
		}
		
	}
}